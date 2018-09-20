package com.waben.stock.datalayer.stockoption.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.FrozenCapitalDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.CapitalAccountInterface;

@Service("stockoptionCapitalAccountBusiness")
public class CapitalAccountBusiness {

	@Autowired
	private CapitalAccountInterface service;

	public CapitalAccountDto fetchByPublisherId(Long publisherId) {
		Response<CapitalAccountDto> response = service.fetchByPublisherId(publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto serviceFeeAndReserveFund(Long publisherId, Long buyRecordId, BigDecimal serviceFee,
			BigDecimal reserveFund, BigDecimal deferredFee) {
		Response<CapitalAccountDto> response = service.serviceFeeAndReserveFund(publisherId, buyRecordId, serviceFee,
				reserveFund, deferredFee);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public FrozenCapitalDto fetchFrozenCapital(Long publisherId, Long buyRecordId) {
		Response<FrozenCapitalDto> response = service.fetchFrozenCapital(publisherId, buyRecordId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto deferredCharges(Long publisherId, Long buyRecordId, BigDecimal deferredCharges) {
		Response<CapitalAccountDto> response = service.deferredCharges(publisherId, buyRecordId, deferredCharges);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto returnReserveFund(Long publisherId, Long buyRecordId, String buyRecordSerialCode,
			BigDecimal profitOrLoss) {
		Response<CapitalAccountDto> response = service.returnReserveFund(publisherId, buyRecordId, buyRecordSerialCode,
				profitOrLoss);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto returnDeferredFee(Long publisherId, Long buyRecordId, BigDecimal deferredFee) {
		Response<CapitalAccountDto> response = service.returnDeferredFee(publisherId, buyRecordId, deferredFee);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto revoke(Long publisherId, Long buyRecordId, BigDecimal serviceFee, BigDecimal deferredFee) {
		Response<CapitalAccountDto> response = service.revoke(publisherId, buyRecordId, serviceFee, deferredFee);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto rightMoney(Long publisherId, Long optionTradeId, BigDecimal rightMoney) {
		Response<CapitalAccountDto> response = service.rightMoney(publisherId, optionTradeId, rightMoney);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto returnRightMoney(Long publisherId, Long optionTradeId, BigDecimal rightMoney) {
		Response<CapitalAccountDto> response = service.returnRightMoney(publisherId, optionTradeId, rightMoney);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto optionProfit(Long publisherId, Long optionTradeId, BigDecimal profit) {
		Response<CapitalAccountDto> response = service.optionProfit(publisherId, optionTradeId, profit);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
