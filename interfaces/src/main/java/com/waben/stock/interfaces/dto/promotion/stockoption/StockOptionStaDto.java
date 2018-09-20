package com.waben.stock.interfaces.dto.promotion.stockoption;

import java.math.BigDecimal;

public class StockOptionStaDto {

	/**
	 * 名义本金总和
	 */
	private BigDecimal totalNominalAmount;
	/**
	 * 权利金总和
	 */
	private BigDecimal totalRightMoney;

	public BigDecimal getTotalNominalAmount() {
		return totalNominalAmount;
	}

	public void setTotalNominalAmount(BigDecimal totalNominalAmount) {
		this.totalNominalAmount = totalNominalAmount;
	}

	public BigDecimal getTotalRightMoney() {
		return totalRightMoney;
	}

	public void setTotalRightMoney(BigDecimal totalRightMoney) {
		this.totalRightMoney = totalRightMoney;
	}

}
