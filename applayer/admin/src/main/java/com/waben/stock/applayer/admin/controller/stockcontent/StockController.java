package com.waben.stock.applayer.admin.controller.stockcontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.admin.business.stockcontent.StockBusiness;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 股票 Controller
 * 
 * @author luomengan
 *
 */
@RestController("adminStockController")
@RequestMapping("/stock")
@Api(description = "股票")
public class StockController {

	@Autowired
	private StockBusiness business;

	@PutMapping("/stockoption/downline/{code}")
	@ApiOperation(value = "期权股票下线")
	public Response<StockDto> stockOptionDownline(@PathVariable("code") String code, String stockOptionBlackRemark) {
		return new Response<>(business.downline(code, stockOptionBlackRemark));
	}

	@PutMapping("/stockoption/online/{code}")
	@ApiOperation(value = "期权股票上线")
	public Response<StockDto> stockOptionOnline(@PathVariable("code") String code) {
		return new Response<>(business.online(code));
	}

}
