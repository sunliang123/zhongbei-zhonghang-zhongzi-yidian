package com.waben.stock.datalayer.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.organization.service.PromotionBuyRecordService;
import com.waben.stock.interfaces.dto.organization.PromotionBuyRecordDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.PromotionBuyRecordQuery;
import com.waben.stock.interfaces.service.organization.PromotionBuyRecordInterface;
import com.waben.stock.interfaces.util.PageToPageInfo;

import io.swagger.annotations.Api;

/**
 * 推广渠道产生的策略 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/promotionBuyRecord")
// @Api(description = "推广渠道产生的策略接口列表")
@Component
public class PromotionBuyRecordController implements PromotionBuyRecordInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PromotionBuyRecordService promotionBuyRecordService;

	@Override
	public Response<PageInfo<PromotionBuyRecordDto>> adminPage(@RequestBody PromotionBuyRecordQuery query) {
		Page<PromotionBuyRecordDto> page = promotionBuyRecordService.pagesByQuery(query);
		PageInfo<PromotionBuyRecordDto> result = PageToPageInfo.pageToPageInfo(page, PromotionBuyRecordDto.class);
		return new Response<>(result);
	}

}
