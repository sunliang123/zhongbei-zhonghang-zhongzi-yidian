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

import com.waben.stock.collector.entity.Settlement;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.SettlementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 结算 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/settlement")
@Api(description = "结算接口列表")
public class SettlementController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public SettlementService settlementService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取结算")
	public DataResponse<Settlement> fetchById(@PathVariable Long id) {
		return new DataResponse<>(settlementService.getSettlementInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取结算分页数据")
	public DataResponse<Page<Settlement>> settlements(int page, int limit) {
		return new DataResponse<>((Page<Settlement>) settlementService.settlements(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取结算列表")
	public DataResponse<List<Settlement>> list() {
		return new DataResponse<>(settlementService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加结算", hidden = true)
	public DataResponse<Settlement> addition(Settlement settlement) {
		return new DataResponse<>(settlementService.addSettlement(settlement));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改结算", hidden = true)
	public DataResponse<Settlement> modification(Settlement settlement) {
		return new DataResponse<>(settlementService.modifySettlement(settlement));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除结算", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		settlementService.deleteSettlement(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除结算（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		settlementService.deleteSettlements(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取结算列表(后台管理)", hidden = true)
	public DataResponse<List<Settlement>> adminList() {
		return new DataResponse<>(settlementService.list());
	}

}
