package com.waben.stock.applayer.strategist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.dto.analogdata.ExperienceSta;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("strategistAnalogDataController")
@RequestMapping("/analogData")
@Api(description = "模拟数据")
public class AnalogDataController {

	@GetMapping("/experienceSta")
	@ApiOperation(value = "体验活动统计")
	public Response<ExperienceSta> lists() {
		ExperienceSta result = new ExperienceSta();
		result.setTotalJoin(9348);
		result.setTodayLeftOver(5000);
		return new Response<>(result);
	}

}
