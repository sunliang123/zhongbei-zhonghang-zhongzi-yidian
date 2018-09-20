package com.waben.stock.applayer.promotion.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.organization.WithdrawalsApplyDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.WithdrawalsApplyQuery;
import com.waben.stock.interfaces.service.organization.WithdrawalsApplyInterface;

/**
 * 提现申请 Business
 * 
 * @author luomengan
 *
 */
@Service("promotionWithdrawalsApplyBusiness")
public class WithdrawalsApplyBusiness {

	@Autowired
	private WithdrawalsApplyInterface reference;

	public WithdrawalsApplyDto addition(WithdrawalsApplyDto apply) {
		Response<WithdrawalsApplyDto> response = reference.addition(apply);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
	public WithdrawalsApplyDto revision(WithdrawalsApplyDto apply) {
		Response<WithdrawalsApplyDto> response = reference.revision(apply);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
	public WithdrawalsApplyDto fetchById(Long id) {
		Response<WithdrawalsApplyDto> response = reference.fetchById(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
	public WithdrawalsApplyDto fetchByApplyNo(String applyNo) {
		Response<WithdrawalsApplyDto> response = reference.fetchByApplyNo(applyNo);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<WithdrawalsApplyDto> pagesByQuery(WithdrawalsApplyQuery applyQuery) {
		Response<PageInfo<WithdrawalsApplyDto>> response = reference.pagesByQuery(applyQuery);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public WithdrawalsApplyDto changeState(Long applyId, String stateIndex, String refusedRemark) {
		Response<WithdrawalsApplyDto> response = reference.changeState(applyId, stateIndex, refusedRemark);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
