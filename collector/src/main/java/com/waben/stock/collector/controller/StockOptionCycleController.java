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

import com.waben.stock.collector.entity.StockOptionCycle;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.StockOptionCycleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期权周期 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/stockOptionCycle")
@Api(description = "期权周期接口列表")
public class StockOptionCycleController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public StockOptionCycleService stockOptionCycleService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取期权周期")
	public DataResponse<StockOptionCycle> fetchById(@PathVariable Long id) {
		return new DataResponse<>(stockOptionCycleService.getStockOptionCycleInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取期权周期分页数据")
	public DataResponse<Page<StockOptionCycle>> stockOptionCycles(int page, int limit) {
		return new DataResponse<>((Page<StockOptionCycle>) stockOptionCycleService.stockOptionCycles(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取期权周期列表")
	public DataResponse<List<StockOptionCycle>> list() {
		return new DataResponse<>(stockOptionCycleService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加期权周期", hidden = true)
	public DataResponse<StockOptionCycle> addition(StockOptionCycle stockOptionCycle) {
		return new DataResponse<>(stockOptionCycleService.addStockOptionCycle(stockOptionCycle));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改期权周期", hidden = true)
	public DataResponse<StockOptionCycle> modification(StockOptionCycle stockOptionCycle) {
		return new DataResponse<>(stockOptionCycleService.modifyStockOptionCycle(stockOptionCycle));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除期权周期", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		stockOptionCycleService.deleteStockOptionCycle(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除期权周期（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		stockOptionCycleService.deleteStockOptionCycles(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取期权周期列表(后台管理)", hidden = true)
	public DataResponse<List<StockOptionCycle>> adminList() {
		return new DataResponse<>(stockOptionCycleService.list());
	}

}
