package com.waben.stock.datalayer.buyrecord.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.inverstors.InvestorInterface;

// @Service("buyrecordInvestorBusiness")
public class InvestorBusiness {

	@Autowired
	private InvestorInterface service;

	public BuyRecordDto voluntarilyStockApplyBuyIn(Long buyRecordId) {
		Response<BuyRecordDto> response = service.voluntarilyStockApplyBuyIn(buyRecordId);
		String code = response.getCode();
		if ("200".equals(code)) {
			return response.getResult();
		} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
			throw new NetflixCircuitException(code);
		}
		throw new ServiceException(response.getCode());
	}

}
