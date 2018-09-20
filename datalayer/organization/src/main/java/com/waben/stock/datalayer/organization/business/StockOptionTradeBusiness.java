package com.waben.stock.datalayer.organization.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.StockOptionTradeInterface;

/**
 * 期权交易 Business
 * 
 * @author luomengan
 */
@Service("organizationStockOptionTradeBusiness")
public class StockOptionTradeBusiness {

	@Autowired
	private StockOptionTradeInterface reference;

	public Integer countStockOptionTradeState(Long publisherId) {
		Response<Integer> response = reference.countStockOptionTradeState(publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
