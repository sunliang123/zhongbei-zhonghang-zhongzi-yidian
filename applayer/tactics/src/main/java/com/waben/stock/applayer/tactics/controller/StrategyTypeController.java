package com.waben.stock.applayer.tactics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.StrategyTypeBusiness;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 策略类型
 * 
 * @author luomengan
 *
 */
@RestController("tacticsStrategyTypeController")
@RequestMapping("/strategytype")
@Api(description = "策略类型")
public class StrategyTypeController {

	@Autowired
	private StrategyTypeBusiness service;

	@GetMapping("/lists")
	@ApiOperation(value = "策略类型列表")
	public Response<List<StrategyTypeDto>> lists() {
		return new Response<>(service.lists());
	}
	
	@GetMapping("/experience")
	@ApiOperation(value = "获取2000元体验活动的策略")
	public Response<StrategyTypeDto> retriveExperienceStrategyType() {
		return new Response<>(service.retriveExperienceStrategyType());
	}

}
