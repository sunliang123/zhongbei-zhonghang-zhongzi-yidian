package com.waben.stock.datalayer.buyrecord.business;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.organization.OrganizationSettlementInterface;

/**
 * 机构结算 Business
 * 
 * @author luomengan
 *
 */
@Service("buyrecordOrganizationSettlementBusiness")
public class OrganizationSettlementBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrganizationSettlementInterface service;

	public void strategySettlement(Long publisherId, Long buyRecordId, String tradeNo, Long strategyTypeId,
			BigDecimal serviceFee, BigDecimal deferredFee) {
		try {
			Response<String> response = service.strategySettlement(publisherId, buyRecordId, tradeNo, strategyTypeId,
					serviceFee, deferredFee);
			String code = response.getCode();
			if ("200".equals(code)) {
				return;
			} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
				throw new NetflixCircuitException(code);
			}
			throw new ServiceException(response.getCode());
		} catch (Exception ex) {
			logger.error("调用机构配资结算发生异常!{}_{}_{}", publisherId, buyRecordId, strategyTypeId);
		}
	}

	public void stockoptionSettlement(Long publisherId, Long stockOptionTradeId, String tradeNo, Long cycleId,
			BigDecimal rightMoneyProfit, BigDecimal rightMoney) {
		try {
			Response<String> response = service.stockoptionSettlement(publisherId, stockOptionTradeId, tradeNo, cycleId,
					rightMoneyProfit, rightMoney);
			String code = response.getCode();
			if ("200".equals(code)) {
				return;
			} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
				throw new NetflixCircuitException(code);
			}
			throw new ServiceException(response.getCode());
		} catch (Exception ex) {
			logger.error("调用机构期权结算发生异常!{}_{}_{}", publisherId, stockOptionTradeId, cycleId);
		}
	}

}
