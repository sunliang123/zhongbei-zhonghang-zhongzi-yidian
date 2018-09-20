package com.waben.stock.datalayer.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.organization.service.PromotionStockOptionTradeService;
import com.waben.stock.interfaces.dto.organization.PromotionStockOptionTradeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.PromotionStockOptionTradeQuery;
import com.waben.stock.interfaces.service.organization.PromotionStockOptionTradeInterface;
import com.waben.stock.interfaces.util.PageToPageInfo;

import io.swagger.annotations.Api;

/**
 * 推广渠道产生的期权交易 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/promotionStockOptionTrade")
// @Api(description = "推广渠道产生的期权交易")
@Component
public class PromotionStockOptionTradeController implements PromotionStockOptionTradeInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PromotionStockOptionTradeService service;

	@Override
	public Response<PageInfo<PromotionStockOptionTradeDto>> adminPage(
			@RequestBody PromotionStockOptionTradeQuery query) {
		Page<PromotionStockOptionTradeDto> page = service.pagesByQuery(query);
		PageInfo<PromotionStockOptionTradeDto> result = PageToPageInfo.pageToPageInfo(page,
				PromotionStockOptionTradeDto.class);
		return new Response<>(result);
	}

}
