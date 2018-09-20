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

import com.waben.stock.applayer.promotion.business.BenefitConfigBusiness;
import com.waben.stock.interfaces.dto.organization.BenefitConfigDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.BenefitConfigForm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 分成配置 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionBenefitConfigController")
@RequestMapping("/benefitConfig")
@Api(description = "分成配置接口列表")
public class BenefitConfigController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BenefitConfigBusiness business;

	@RequestMapping(value = "/benefitConfigList", method = RequestMethod.GET)
	@ApiOperation(value = "获取返佣比例列表")
	public Response<List<BenefitConfigDto>> benefitConfigList(Long orgId, Integer resourceType) {
		return new Response<>(business.benefitConfigList(orgId, resourceType));
	}

	@RequestMapping(value = "/strategy/config", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> strategyBenefitConfig(@RequestBody List<BenefitConfigForm> configFormList) {
		String result = business.strategyBenefitConfig(configFormList);
		Response<String> response = new Response<>();
		response.setResult(result);
		return response;
	}

	@RequestMapping(value = "/stockoption/config", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "编辑返佣比例")
	public Response<String> stockoptionBenefitConfig(@RequestBody List<BenefitConfigForm> configFormList) {
		String result = business.stockoptionBenefitConfig(configFormList);
		Response<String> response = new Response<>();
		response.setResult(result);
		return response;
	}

}
