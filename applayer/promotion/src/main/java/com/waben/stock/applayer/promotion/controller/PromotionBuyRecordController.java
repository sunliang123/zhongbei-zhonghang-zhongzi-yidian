package com.waben.stock.applayer.promotion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.PromotionBuyRecordBusiness;
import com.waben.stock.interfaces.dto.organization.PromotionBuyRecordDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.PromotionBuyRecordQuery;

import io.swagger.annotations.Api;

/**
 * 推广渠道产生的策略 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionPromotionBuyRecordController")
@RequestMapping("/promotionBuyRecord")
@Api(description = "推广渠道产生的策略接口列表")
public class PromotionBuyRecordController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PromotionBuyRecordBusiness business;

	@RequestMapping(value = "/adminPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<PageInfo<PromotionBuyRecordDto>> adminPage(@RequestBody PromotionBuyRecordQuery query) {
		return new Response<>(business.adminPage(query));
	}

}
