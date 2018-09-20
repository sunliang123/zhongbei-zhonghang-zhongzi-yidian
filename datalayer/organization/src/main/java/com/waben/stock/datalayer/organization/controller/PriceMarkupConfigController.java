package com.waben.stock.datalayer.organization.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.organization.entity.PriceMarkupConfig;
import com.waben.stock.datalayer.organization.service.PriceMarkupConfigService;
import com.waben.stock.interfaces.dto.organization.PriceMarkupConfigDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.PriceMarkupForm;
import com.waben.stock.interfaces.service.organization.PriceMarkupConfigInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 加价配置 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/priceMarkupConfig")
// @Api(description = "加价配置接口列表")
@Component
public class PriceMarkupConfigController implements PriceMarkupConfigInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PriceMarkupConfigService priceMarkupConfigService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取加价配置")
	public Response<PriceMarkupConfig> fetchById(@PathVariable Long id) {
		return new Response<>(priceMarkupConfigService.getOptionPriceConfigInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取加价配置分页数据")
	public Response<Page<PriceMarkupConfig>> priceMarkupConfigs(int page, int limit) {
		return new Response<>((Page<PriceMarkupConfig>) priceMarkupConfigService.priceMarkupConfigs(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取加价配置列表")
	public Response<List<PriceMarkupConfig>> list() {
		return new Response<>(priceMarkupConfigService.list());
	}

	/******************************** 后台管理 **********************************/

	@PostMapping("/")
	@ApiOperation(value = "添加加价配置", hidden = true)
	public Response<PriceMarkupConfig> addition(PriceMarkupConfig priceMarkupConfig) {
		return new Response<>(priceMarkupConfigService.addOptionPriceConfig(priceMarkupConfig));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改加价配置", hidden = true)
	public Response<PriceMarkupConfig> modification(PriceMarkupConfig priceMarkupConfig) {
		return new Response<>(priceMarkupConfigService.modifyOptionPriceConfig(priceMarkupConfig));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除加价配置", hidden = true)
	public Response<Long> delete(@PathVariable Long id) {
		priceMarkupConfigService.deleteOptionPriceConfig(id);
		return new Response<Long>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除加价配置（多个id以逗号分割）", hidden = true)
	public Response<Boolean> deletes(String ids) {
		priceMarkupConfigService.deleteOptionPriceConfigs(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/adminList")
	@ApiOperation(value = "获取加价配置列表(后台管理)", hidden = true)
	public Response<List<PriceMarkupConfig>> adminList() {
		return new Response<>(priceMarkupConfigService.list());
	}

	@Override
	public Response<List<PriceMarkupConfigDto>> priceMarkupConfigList(@RequestParam(required = true) Long orgId,
			@RequestParam(required = true) Integer resourceType) {
		return new Response<>(priceMarkupConfigService.priceMarkupConfigList(orgId, resourceType));
	}

	@Override
	public Response<String> priceMarkupConfig(@RequestBody List<PriceMarkupForm> configFormList) {
		priceMarkupConfigService.priceMarkupConfig(configFormList);
		Response<String> result = new Response<>();
		result.setResult("success");
		return result;
	}

	@Override
	public Response<List<BigDecimal>> priceMarkupRatioList(Integer resourceType, Long resourceId, Long publisherId) {
		return new Response<>(priceMarkupConfigService.priceMarkupRatioList(resourceType, resourceId, publisherId));
	}

}
