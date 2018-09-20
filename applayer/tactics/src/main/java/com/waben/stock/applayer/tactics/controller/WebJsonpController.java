package com.waben.stock.applayer.tactics.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.BuyRecordBusiness;
import com.waben.stock.applayer.tactics.business.ExperienceBusiness;
import com.waben.stock.applayer.tactics.dto.analogdata.ExperienceSta;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.JacksonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("tacticsWebJsonpController")
@RequestMapping("/jsonp")
@Api(description = "模拟数据")
public class WebJsonpController {

	@Autowired
	private BuyRecordBusiness buyRecordBusiness;

	@Autowired
	private ExperienceBusiness experienceBusiness;

	@GetMapping("/experienceSta")
	@ApiOperation(value = "体验活动统计")
	public void lists(HttpServletRequest request, HttpServletResponse response) {
		ExperienceSta sta = new ExperienceSta();
		sta.setTotalJoin(experienceBusiness.getTotalJoin());
		sta.setTodayLeftOver(experienceBusiness.getTodayLeftOver());
		Response<ExperienceSta> result = new Response<>(sta);
		String callback = request.getParameter("callback");
		String jsonp = callback + "(" + JacksonUtil.encode(result) + ");";
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			response.getWriter().write(jsonp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/{publisherId}/strategyqualify/{strategyTypeId}")
	@ApiOperation(value = "是否有资格参与某个策略")
	public void hasStrategyQualify(@PathVariable("publisherId") Long publisherId,
			@PathVariable("strategyTypeId") Long strategyTypeId, HttpServletRequest request,
			HttpServletResponse response) {
		Response<Boolean> result = new Response<>(buyRecordBusiness.hasStrategyQualify(publisherId, strategyTypeId));
		String callback = request.getParameter("callback");
		String jsonp = callback + "(" + JacksonUtil.encode(result) + ");";
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			response.getWriter().write(jsonp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
