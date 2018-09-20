package com.waben.stock.applayer.tactics.controller;

import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.CapitalFlowBusiness;
import com.waben.stock.applayer.tactics.dto.publisher.CapitalFlowWithExtendDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CapitalFlowQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资金流水 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsCapitalFlowController")
@RequestMapping("/capitalFlow")
@Api(description = "资金流水")
public class CapitalFlowController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CapitalFlowBusiness capitalFlowBusiness;

	@GetMapping("/pages")
	@ApiOperation(value = "用户资金流水", notes = "range表示统计范围，0全部，1最近一周，2最近一个月，3最近半年")
	public Response<PageInfo<CapitalFlowWithExtendDto>> publisherCapitalFlow(int page, int size,
			@RequestParam(defaultValue = "0") int range) {
		CapitalFlowQuery query = new CapitalFlowQuery(page, size);
		Date startTime = null;
		if (range == 1) {
			startTime = new DateTime(new Date()).minusHours(7 * 24).toDate();
		} else if (range == 2) {
			startTime = new DateTime(new Date()).minusHours(30 * 24).toDate();
		} else if (range == 3) {
			startTime = new DateTime(new Date()).minusHours(180 * 24).toDate();
		}
		query.setStartTime(startTime);
		query.setPublisherId(SecurityUtil.getUserId());
		return new Response<>(capitalFlowBusiness.pages(query));
	}

}
