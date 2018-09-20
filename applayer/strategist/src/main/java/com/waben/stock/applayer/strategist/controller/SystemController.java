package com.waben.stock.applayer.strategist.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.business.BannerBusiness;
import com.waben.stock.applayer.strategist.business.CircularsBusiness;
import com.waben.stock.applayer.strategist.dto.system.AppHomeTopDataDto;
import com.waben.stock.applayer.strategist.dto.system.StockMarketExponentDto;
import com.waben.stock.applayer.strategist.service.StockMarketService;
import com.waben.stock.interfaces.dto.manage.BannerDto;
import com.waben.stock.interfaces.dto.manage.CircularsDto;
import com.waben.stock.interfaces.enums.BannerForwardCategory;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BannerQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Created by yuyidi on 2017/11/7.
 * @desc
 */
@RestController("strategistSystemController")
@RequestMapping("/system")
@Api(description = "系统")
public class SystemController {

	@Value("${spring.jpa.show-sql:error}")
	private String show;

	@GetMapping("/index")
	public String index() {
		return show;
	}

	@Autowired
	private BannerBusiness bannerBusiness;

	@Autowired
	private CircularsBusiness circularsBusiness;

	@Autowired
	private StockMarketService stockMarketService;

	@GetMapping("/getEnabledBannerList")
	@ApiOperation(value = "获取轮播图列表")
	public Response<List<BannerDto>> getBannerList() {
		BannerQuery query = new BannerQuery();
		query.setPage(0);
		query.setSize(10);
		query.setCategory(BannerForwardCategory.PC);
		PageInfo<BannerDto> pages = bannerBusiness.pages(query);
		return new Response<>(pages.getContent());
	}

	@GetMapping("/getEnabledCircularsList")
	@ApiOperation(value = "获取通告列表")
	public Response<List<CircularsDto>> getCircularsList() {
		return new Response<>(circularsBusiness.fetchCirculars(true));
	}

	@GetMapping("/stockMarketExponent")
	@ApiOperation(value = "获取大盘指数")
	public Response<List<StockMarketExponentDto>> stockMarketExponent() {
		return new Response<>(stockMarketService.listStockExponent());
	}

	@GetMapping("/getAppHomeTopData")
	@ApiOperation(value = "获取APP首页顶部数据（轮播图、公告、大盘指数）")
	public Response<AppHomeTopDataDto> getAppHomeTopData() {
		Response<AppHomeTopDataDto> result = new Response<>(new AppHomeTopDataDto());
		// 获取轮播图
		BannerQuery query = new BannerQuery();
		query.setPage(0);
		query.setSize(10);
		query.setCategory(BannerForwardCategory.PC);
		PageInfo<BannerDto> pages = bannerBusiness.pages(query);
		result.getResult().setBannerList(pages.getContent());
		// 获取公告
		result.getResult().setCircularsList(circularsBusiness.fetchCirculars(true));
		// 获取股票市场指数
		result.getResult().setStockMarketIndexList(stockMarketService.listStockExponent());
		return result;
	}
	
	@GetMapping("/serverTime")
	@ApiOperation(value = "获取服务器最新时间")
	public Response<String> serverTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Response<String> result = new Response<>();
		result.setResult(sdf.format(new Date()));
		return result;
	}

}
