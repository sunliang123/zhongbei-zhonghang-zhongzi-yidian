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

import com.waben.stock.collector.entity.OfflineStockOptionTrade;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.OfflineStockOptionTradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 线下期权交易信息 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/offlineStockOptionTrade")
@Api(description = "线下期权交易信息接口列表")
public class OfflineStockOptionTradeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OfflineStockOptionTradeService offlineStockOptionTradeService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取线下期权交易信息")
	public DataResponse<OfflineStockOptionTrade> fetchById(@PathVariable Long id) {
		return new DataResponse<>(offlineStockOptionTradeService.getOfflineStockOptionTradeInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取线下期权交易信息分页数据")
	public DataResponse<Page<OfflineStockOptionTrade>> offlineStockOptionTrades(int page, int limit) {
		return new DataResponse<>((Page<OfflineStockOptionTrade>) offlineStockOptionTradeService.offlineStockOptionTrades(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取线下期权交易信息列表")
	public DataResponse<List<OfflineStockOptionTrade>> list() {
		return new DataResponse<>(offlineStockOptionTradeService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加线下期权交易信息", hidden = true)
	public DataResponse<OfflineStockOptionTrade> addition(OfflineStockOptionTrade offlineStockOptionTrade) {
		return new DataResponse<>(offlineStockOptionTradeService.addOfflineStockOptionTrade(offlineStockOptionTrade));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改线下期权交易信息", hidden = true)
	public DataResponse<OfflineStockOptionTrade> modification(OfflineStockOptionTrade offlineStockOptionTrade) {
		return new DataResponse<>(offlineStockOptionTradeService.modifyOfflineStockOptionTrade(offlineStockOptionTrade));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除线下期权交易信息", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		offlineStockOptionTradeService.deleteOfflineStockOptionTrade(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除线下期权交易信息（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		offlineStockOptionTradeService.deleteOfflineStockOptionTrades(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取线下期权交易信息列表(后台管理)", hidden = true)
	public DataResponse<List<OfflineStockOptionTrade>> adminList() {
		return new DataResponse<>(offlineStockOptionTradeService.list());
	}

}
