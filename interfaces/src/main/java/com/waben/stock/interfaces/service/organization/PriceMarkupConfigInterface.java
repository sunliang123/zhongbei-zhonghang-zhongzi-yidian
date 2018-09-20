package com.waben.stock.interfaces.service.organization;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.organization.PriceMarkupConfigDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.PriceMarkupForm;

public interface PriceMarkupConfigInterface {

	/**
	 * 获取某个机构的加价配置
	 * 
	 * @param orgId
	 *            机构ID
	 * @param resourceType
	 *            分成类型：1策略分成,2期权分成
	 * @return 加价配置列表
	 */
	@RequestMapping(value = "/priceMarkupConfigList", method = RequestMethod.GET)
	public Response<List<PriceMarkupConfigDto>> priceMarkupConfigList(@RequestParam("orgId") Long orgId,
			@RequestParam("resourceType") Integer resourceType);

	/**
	 * 配置加价比例
	 * 
	 * @param configFormList
	 *            加价配置表单数据列表
	 */
	@RequestMapping(value = "/priceMarkupConfig", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> priceMarkupConfig(@RequestBody List<PriceMarkupForm> configFormList);

	/**
	 * 获取加价比例List，自顶向下
	 */
	@RequestMapping(value = "/priceMarkupRatioList", method = RequestMethod.GET)
	public Response<List<BigDecimal>> priceMarkupRatioList(@RequestParam("resourceType") Integer resourceType,
			@RequestParam("resourceId") Long resourceId, @RequestParam("publisherId") Long publisherId);

}