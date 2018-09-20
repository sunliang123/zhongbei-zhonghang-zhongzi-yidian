package com.waben.stock.interfaces.service.organization;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.organization.BenefitConfigDto;
import com.waben.stock.interfaces.dto.organization.SettlementMethodDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.BenefitConfigForm;

public interface BenefitConfigInterface {

	/**
	 * 获取某个机构的分成配置
	 * 
	 * @param orgId
	 *            机构ID
	 * @param resourceType
	 *            分成类型：1策略分成,2期权分成
	 * @return 分成配置列表
	 */
	@RequestMapping(value = "/benefitConfigList", method = RequestMethod.GET)
	Response<List<BenefitConfigDto>> benefitConfigList(@RequestParam("orgId") Long orgId,
			@RequestParam("resourceType") Integer resourceType);

	/**
	 * 配置策略分成
	 * 
	 * @param configFormList
	 *            分成配置表单数据列表
	 */
	@RequestMapping(value = "/strategy/config", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<String> strategyBenefitConfig(@RequestBody List<BenefitConfigForm> configFormList);

	/**
	 * 配置期权分成
	 * 
	 * @param configFormList
	 *            分成配置表单数据列表
	 */
	@RequestMapping(value = "/stockoption/config", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<String> stockoptionBenefitConfig(@RequestBody List<BenefitConfigForm> configFormList);
	
	@RequestMapping(value = "/settlement/method", method = RequestMethod.GET)
	Response<SettlementMethodDto> getSettlement();
	
	

}