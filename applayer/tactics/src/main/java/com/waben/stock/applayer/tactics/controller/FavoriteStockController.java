package com.waben.stock.applayer.tactics.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.FavoriteStockBusiness;
import com.waben.stock.applayer.tactics.business.StockBusiness;
import com.waben.stock.applayer.tactics.dto.publisher.FavoriteStockWithMarketDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.dto.publisher.FavoriteStockDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 收藏股票 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsFavoriteStockController")
@RequestMapping("/favoriteStock")
@Api(description = "自选收藏股票")
public class FavoriteStockController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FavoriteStockBusiness favoriteBusiness;

	@Autowired
	private StockBusiness stockBusiness;

	@PostMapping("/addFavoriteStock")
	@ApiOperation(value = "收藏股票")
	public Response<FavoriteStockDto> addFavoriteStock(@RequestParam(required = true) String stockCode) {
		StockDto stockDto = stockBusiness.findByCode(stockCode);
		FavoriteStockDto favorite = new FavoriteStockDto();
		favorite.setCode(stockDto.getCode());
		favorite.setName(stockDto.getName());
		favorite.setExponentCode(
				stockDto.getExponent() != null ? stockDto.getExponent().getExponentCode() : null);
		favorite.setPublisherId(SecurityUtil.getUserId());
		return new Response<>(favoriteBusiness.save(favorite));
	}

	@PostMapping("/removeFavoriteStock")
	@ApiOperation(value = "删除收藏股票")
	public Response<String> removeFavoriteStock(@RequestParam(required = true) String[] stockCodes) {
		StringBuilder stockCodesStr = new StringBuilder();
		for (int i = 0; i < stockCodes.length; i++) {
			stockCodesStr.append(stockCodes[i]);
			if (i != stockCodes.length - 1) {
				stockCodesStr.append("-");
			}
		}
		favoriteBusiness.remove(SecurityUtil.getUserId(), stockCodesStr.toString());
		return new Response<>();
	}

	@PostMapping("/topFavoriteStock")
	@ApiOperation(value = "置顶收藏股票")
	public Response<String> topFavoriteStock(@RequestParam(required = true) String[] stockCodes) {
		StringBuilder stockCodesStr = new StringBuilder();
		for (int i = 0; i < stockCodes.length; i++) {
			stockCodesStr.append(stockCodes[i]);
			if (i != stockCodes.length - 1) {
				stockCodesStr.append("-");
			}
		}
		favoriteBusiness.top(SecurityUtil.getUserId(), stockCodesStr.toString());
		return new Response<>();
	}

	@GetMapping("/favoriteStockList")
	@ApiOperation(value = "获取收藏股票")
	public Response<List<FavoriteStockWithMarketDto>> favoriteStockList() {
		return new Response<>(favoriteBusiness.listsByPublisherId(SecurityUtil.getUserId()));
	}

}
