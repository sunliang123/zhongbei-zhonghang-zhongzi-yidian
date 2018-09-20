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

import com.waben.stock.collector.entity.CapitalFlow;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.CapitalFlowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资金流水 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/capitalFlow")
@Api(description = "资金流水接口列表")
public class CapitalFlowController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public CapitalFlowService capitalFlowService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取资金流水")
	public DataResponse<CapitalFlow> fetchById(@PathVariable Long id) {
		return new DataResponse<>(capitalFlowService.getCapitalFlowInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取资金流水分页数据")
	public DataResponse<Page<CapitalFlow>> capitalFlows(int page, int limit) {
		return new DataResponse<>((Page<CapitalFlow>) capitalFlowService.capitalFlows(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取资金流水列表")
	public DataResponse<List<CapitalFlow>> list() {
		return new DataResponse<>(capitalFlowService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加资金流水", hidden = true)
	public DataResponse<CapitalFlow> addition(CapitalFlow capitalFlow) {
		return new DataResponse<>(capitalFlowService.addCapitalFlow(capitalFlow));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改资金流水", hidden = true)
	public DataResponse<CapitalFlow> modification(CapitalFlow capitalFlow) {
		return new DataResponse<>(capitalFlowService.modifyCapitalFlow(capitalFlow));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除资金流水", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		capitalFlowService.deleteCapitalFlow(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除资金流水（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		capitalFlowService.deleteCapitalFlows(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取资金流水列表(后台管理)", hidden = true)
	public DataResponse<List<CapitalFlow>> adminList() {
		return new DataResponse<>(capitalFlowService.list());
	}

}
