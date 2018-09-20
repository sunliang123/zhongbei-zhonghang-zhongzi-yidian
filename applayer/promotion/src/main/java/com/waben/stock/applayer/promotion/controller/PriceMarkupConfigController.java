package com.waben.stock.applayer.promotion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.PriceMarkupConfigBusiness;
import com.waben.stock.interfaces.dto.organization.PriceMarkupConfigDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.PriceMarkupForm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 加价配置 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionPriceMarkupConfigController")
@RequestMapping("/priceMarkupConfig")
@Api(description = "加价配置接口列表")
public class PriceMarkupConfigController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PriceMarkupConfigBusiness business;

	@RequestMapping(value = "/priceMarkupConfigList", method = RequestMethod.GET)
	@ApiOperation(value = "获取加价比例列表")
	public Response<List<PriceMarkupConfigDto>> priceMarkupConfigList(Long orgId, Integer resourceType) {
		return new Response<>(business.priceMarkupConfigList(orgId, resourceType));
	}

	@RequestMapping(value = "/priceMarkupConfig", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "编辑加价比例")
	public Response<String> priceMarkupConfig(@RequestBody List<PriceMarkupForm> configFormList) {
		String result = business.priceMarkupConfig(configFormList);
		Response<String> response = new Response<>();
		response.setResult(result);
		return response;
	}

}
