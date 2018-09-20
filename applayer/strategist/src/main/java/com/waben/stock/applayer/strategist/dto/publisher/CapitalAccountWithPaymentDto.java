package com.waben.stock.applayer.strategist.dto.publisher;

import java.math.BigDecimal;

import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;

public class CapitalAccountWithPaymentDto extends CapitalAccountDto {

	/**
	 * 累计充值金额
	 */
	private BigDecimal totalRechargeAmount;
	/**
	 * 累计提现金额
	 */
	private BigDecimal totalWithdrawalsAmount;
	
	public CapitalAccountWithPaymentDto(CapitalAccountDto account) {
		this.setAvailableBalance(account.getAvailableBalance());
		this.setBalance(account.getBalance());
		this.setFrozenCapital(account.getFrozenCapital());
		this.setPublisherId(account.getPublisherId());
		this.setPublisherSerialCode(account.getPublisherSerialCode());
		this.setUpdateTime(account.getUpdateTime());
	}

	public BigDecimal getTotalRechargeAmount() {
		return totalRechargeAmount;
	}

	public void setTotalRechargeAmount(BigDecimal totalRechargeAmount) {
		this.totalRechargeAmount = totalRechargeAmount;
	}

	public BigDecimal getTotalWithdrawalsAmount() {
		return totalWithdrawalsAmount;
	}

	public void setTotalWithdrawalsAmount(BigDecimal totalWithdrawalsAmount) {
		this.totalWithdrawalsAmount = totalWithdrawalsAmount;
	}

}
