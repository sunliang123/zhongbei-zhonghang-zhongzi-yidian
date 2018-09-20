package com.waben.stock.datalayer.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.organization.entity.WithdrawalsApply;
import com.waben.stock.datalayer.organization.service.WithdrawalsApplyService;
import com.waben.stock.interfaces.dto.organization.WithdrawalsApplyDto;
import com.waben.stock.interfaces.enums.WithdrawalsApplyState;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.WithdrawalsApplyQuery;
import com.waben.stock.interfaces.service.organization.WithdrawalsApplyInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

import io.swagger.annotations.Api;

/**
 * 提现申请 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/withdrawalsApply")
// @Api(description = "提现申请接口列表")
@Component
public class WithdrawalsApplyController implements WithdrawalsApplyInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public WithdrawalsApplyService service;

	@Override
	public Response<WithdrawalsApplyDto> addition(@RequestBody WithdrawalsApplyDto apply) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(WithdrawalsApplyDto.class, service.addWithdrawalsApply(
				CopyBeanUtils.copyBeanProperties(WithdrawalsApply.class, apply, false), apply.getOrgId()), false));
	}

	@Override
	public Response<WithdrawalsApplyDto> revision(@RequestBody WithdrawalsApplyDto apply) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(WithdrawalsApplyDto.class, service.revisionWithdrawalsApply(
						CopyBeanUtils.copyBeanProperties(WithdrawalsApply.class, apply, false)), false));
	}

	@Override
	public Response<PageInfo<WithdrawalsApplyDto>> pagesByQuery(@RequestBody WithdrawalsApplyQuery applyQuery) {
		Page<WithdrawalsApply> page = service.pagesByQuery(applyQuery);
		PageInfo<WithdrawalsApplyDto> result = PageToPageInfo.pageToPageInfo(page, WithdrawalsApplyDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<WithdrawalsApplyDto> changeState(@PathVariable Long applyId, String stateIndex, String refusedRemark) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(WithdrawalsApplyDto.class,
				service.changeState(applyId, WithdrawalsApplyState.getByIndex(stateIndex), refusedRemark), false));
	}

	@Override
	public Response<WithdrawalsApplyDto> fetchById(@PathVariable Long id) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(WithdrawalsApplyDto.class, service.findById(id), false));
	}

	@Override
	public Response<WithdrawalsApplyDto> fetchByApplyNo(@PathVariable String applyNo) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(WithdrawalsApplyDto.class, service.findByApplyNo(applyNo), false));
	}

}
