package com.waben.stock.collector.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.collector.entity.StrategyType;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.StrategyTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 策略类型 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/strategyType")
@Api(description = "策略类型接口列表")
public class StrategyTypeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public StrategyTypeService strategyTypeService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取策略类型")
	public DataResponse<StrategyType> fetchById(@PathVariable Long id) {
		return new DataResponse<>(strategyTypeService.getStrategyTypeInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取策略类型分页数据")
	public DataResponse<Page<StrategyType>> strategyTypes(int page, int limit) {
		return new DataResponse<>((Page<StrategyType>) strategyTypeService.strategyTypes(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取策略类型列表")
	public DataResponse<List<StrategyType>> list() {
		return new DataResponse<>(strategyTypeService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加策略类型", hidden = true)
	public DataResponse<StrategyType> addition(StrategyType strategyType) {
		return new DataResponse<>(strategyTypeService.addStrategyType(strategyType));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改策略类型", hidden = true)
	public DataResponse<StrategyType> modification(StrategyType strategyType) {
		return new DataResponse<>(strategyTypeService.modifyStrategyType(strategyType));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除策略类型", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		strategyTypeService.deleteStrategyType(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除策略类型（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		strategyTypeService.deleteStrategyTypes(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取策略类型列表(后台管理)", hidden = true)
	public DataResponse<List<StrategyType>> adminList() {
		return new DataResponse<>(strategyTypeService.list());
	}

}
