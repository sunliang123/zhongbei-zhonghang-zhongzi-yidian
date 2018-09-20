package com.waben.stock.datalayer.publisher.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.publisher.entity.CapitalFlow;
import com.waben.stock.datalayer.publisher.service.CapitalFlowService;
import com.waben.stock.interfaces.dto.admin.publisher.CapitalFlowAdminDto;
import com.waben.stock.interfaces.dto.publisher.CapitalFlowDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CapitalFlowQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalFlowAdminQuery;
import com.waben.stock.interfaces.service.publisher.CapitalFlowInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * 资金流水 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/capitalFlow")
@Component
public class CapitalFlowController implements CapitalFlowInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CapitalFlowService capitalFlowService;

	@Override
	public Response<PageInfo<CapitalFlowAdminDto>> adminPagesByQuery(@RequestBody CapitalFlowAdminQuery query) {
		Page<CapitalFlowAdminDto> page = capitalFlowService.adminPagesByQuery(query);
		PageInfo<CapitalFlowAdminDto> result = PageToPageInfo.pageToPageInfo(page, CapitalFlowAdminDto.class);
		return new Response<>(result);
	}
	
	@Override
	public Response<BigDecimal> adminAccumulateAmountByQuery(@RequestBody CapitalFlowAdminQuery query) {
		return new Response<>(capitalFlowService.adminAccumulateAmountByQuery(query));
	}

	@Override
	public Response<PageInfo<CapitalFlowDto>> pagesByQuery(@RequestBody CapitalFlowQuery query) {
		Page<CapitalFlow> page = capitalFlowService.pagesByQuery(query);
		PageInfo<CapitalFlowDto> result = PageToPageInfo.pageToPageInfo(page, CapitalFlowDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<BigDecimal> promotionTotalAmount(@PathVariable Long publisherId) {
		return new Response<>(capitalFlowService.promotionTotalAmount(publisherId));
	}

	@Override
	public Response<CapitalFlowDto> fetchById(@PathVariable Long capitalFlowId) {
		CapitalFlow capitalFlow = capitalFlowService.findById(capitalFlowId);
		CapitalFlowDto capitalFlowDto = CopyBeanUtils.copyBeanProperties(CapitalFlowDto.class, capitalFlow, false);
		return new Response<>(capitalFlowDto);
	}

}
