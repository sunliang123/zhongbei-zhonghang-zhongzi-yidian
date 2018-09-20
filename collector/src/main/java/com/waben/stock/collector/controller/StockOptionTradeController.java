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

import com.waben.stock.collector.entity.StockOptionTrade;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.StockOptionTradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户股票期权交易信息 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/stockOptionTrade")
@Api(description = "用户股票期权交易信息接口列表")
public class StockOptionTradeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public StockOptionTradeService stockOptionTradeService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取用户股票期权交易信息")
	public DataResponse<StockOptionTrade> fetchById(@PathVariable Long id) {
		return new DataResponse<>(stockOptionTradeService.getStockOptionTradeInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取用户股票期权交易信息分页数据")
	public DataResponse<Page<StockOptionTrade>> stockOptionTrades(int page, int limit) {
		return new DataResponse<>((Page<StockOptionTrade>) stockOptionTradeService.stockOptionTrades(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取用户股票期权交易信息列表")
	public DataResponse<List<StockOptionTrade>> list() {
		return new DataResponse<>(stockOptionTradeService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加用户股票期权交易信息", hidden = true)
	public DataResponse<StockOptionTrade> addition(StockOptionTrade stockOptionTrade) {
		return new DataResponse<>(stockOptionTradeService.addStockOptionTrade(stockOptionTrade));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改用户股票期权交易信息", hidden = true)
	public DataResponse<StockOptionTrade> modification(StockOptionTrade stockOptionTrade) {
		return new DataResponse<>(stockOptionTradeService.modifyStockOptionTrade(stockOptionTrade));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除用户股票期权交易信息", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		stockOptionTradeService.deleteStockOptionTrade(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除用户股票期权交易信息（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		stockOptionTradeService.deleteStockOptionTrades(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取用户股票期权交易信息列表(后台管理)", hidden = true)
	public DataResponse<List<StockOptionTrade>> adminList() {
		return new DataResponse<>(stockOptionTradeService.list());
	}

}
