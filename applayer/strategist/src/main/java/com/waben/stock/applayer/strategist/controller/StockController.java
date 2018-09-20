package com.waben.stock.applayer.strategist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.business.FavoriteStockBusiness;
import com.waben.stock.applayer.strategist.business.StockBusiness;
import com.waben.stock.applayer.strategist.dto.stockcontent.StockDiscDto;
import com.waben.stock.applayer.strategist.dto.stockcontent.StockMarketWithFavoriteDto;
import com.waben.stock.applayer.strategist.dto.stockcontent.StockRecommendWithMarketDto;
import com.waben.stock.applayer.strategist.dto.stockcontent.StockWithFavoriteDto;
import com.waben.stock.applayer.strategist.security.SecurityUtil;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockKLine;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockTimeLine;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockQuery;
import com.waben.stock.interfaces.util.CopyBeanUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 股票 Controller
 * 
 * @author luomengan
 *
 */
@RestController("strategistStockController")
@RequestMapping("/stock")
@Api(description = "股票")
public class StockController {

	@Autowired
	private StockBusiness stockBusiness;

	@Autowired
	private FavoriteStockBusiness favoriteStockBusiness;

	@GetMapping("/selectStock")
	@ApiOperation(value = "查询股票，匹配股票名称/代码/简拼")
	public Response<List<StockWithFavoriteDto>> selectStock(String keyword) {
		if (keyword == null || "".equals(keyword.trim())) {
			List<StockWithFavoriteDto> content = new ArrayList<>();
			return new Response<>(content);
		}
		StockQuery stockQuery = new StockQuery();
		stockQuery.setKeyword(keyword);
		stockQuery.setPage(0);
		stockQuery.setSize(20);
		PageInfo<StockDto> pages = stockBusiness.pages(stockQuery);
		List<StockWithFavoriteDto> content = CopyBeanUtils.copyListBeanPropertiesToList(pages.getContent(),
				StockWithFavoriteDto.class);
		if (content != null && content.size() > 0) {
			Long publisherId = SecurityUtil.getUserId();
			if (publisherId != null) {
				List<String> stockCodes = favoriteStockBusiness.listsStockCode(publisherId);
				if (stockCodes != null && stockCodes.size() > 0) {
					for (StockWithFavoriteDto stockDto : content) {
						if (stockCodes.contains(stockDto.getCode())) {
							stockDto.setFavorite(true);
						}
					}
				}
			}
		}
		return new Response<>(content);
	}

	@GetMapping("/stockRecommend")
	@ApiOperation(value = "获取股票推荐列表")
	public Response<PageInfo<StockRecommendWithMarketDto>> stockRecommend(int page, int size) {
		return new Response<>(stockBusiness.stockRecommend(page, size));
	}

	@GetMapping("/market/{code}")
	@ApiOperation(value = "获取某支股票的最新行情")
	public Response<StockMarketWithFavoriteDto> marketByCode(@PathVariable("code") String code) {
		return new Response<>(stockBusiness.marketByCode(code));
	}

	@GetMapping("/kLine")
	@ApiOperation(value = "获取K线图数据", notes = "type:1表示天K，2表示周K，3表示月K； startTime和endTime格式为:yyyy-MM-DD HH:mm:ss")
	public Response<List<StockKLine>> listKLine(String stockCode, Integer type, String startTime, String endTime) {
		return new Response<>(stockBusiness.listKLine(stockCode, type, startTime, endTime, -1));
	}
	
	@GetMapping("/timeLine/{code}")
	@ApiOperation(value = "获取分时图数据")
	public Response<List<StockTimeLine>> listTimeLine(@PathVariable("code") String code) {
		return new Response<>(stockBusiness.listTimeLine(code));
	}

	@GetMapping("/disc/{code}")
	@ApiOperation(value = "盘口")
	public Response<StockDiscDto> disc(@PathVariable("code") String code) {
		return new Response<>(stockBusiness.disc(code));
	}
	
	@GetMapping("/{exponent}/ranking")
	@ApiOperation(value = "大盘股票跌涨排行榜", notes = "1涨幅榜，2跌幅榜，3价格降序，4，价格升序")
	public Response<List<StockMarket>> ranking(@PathVariable("exponent") String exponent, int rankType, int size) {
		return new Response<>(stockBusiness.ranking(exponent, rankType, size));
	}

}
