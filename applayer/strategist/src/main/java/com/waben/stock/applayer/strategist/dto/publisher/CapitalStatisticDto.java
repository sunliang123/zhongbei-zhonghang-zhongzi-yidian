package com.waben.stock.applayer.strategist.dto.publisher;

import java.math.BigDecimal;

/**
 * 资金统计
 * 
 * @author luomengan
 *
 */
public class CapitalStatisticDto {

	/**
	 * 持仓中的盈亏，统计持仓中的记录
	 */
	private BigDecimal holdProfitOrLoss;
	/**
	 * 总共点买的申请资金，统计所有的记录
	 */
	private BigDecimal totalApplyAmount;
	/**
	 * 今天点买的申请资金，统计今天的记录
	 */
	private BigDecimal todayApplyAmount;
	/**
	 * 持仓中的冻结资金，统计持仓中的记录
	 */
	private BigDecimal frozenAmount;
	/**
	 * 持仓中的递延费，统计持仓中的记录
	 */
	private BigDecimal deferredAmount = new BigDecimal(0);

	public BigDecimal getHoldProfitOrLoss() {
		return holdProfitOrLoss;
	}

	public void setHoldProfitOrLoss(BigDecimal holdProfitOrLoss) {
		this.holdProfitOrLoss = holdProfitOrLoss;
	}

	public BigDecimal getTotalApplyAmount() {
		return totalApplyAmount;
	}

	public void setTotalApplyAmount(BigDecimal totalApplyAmount) {
		this.totalApplyAmount = totalApplyAmount;
	}

	public BigDecimal getTodayApplyAmount() {
		return todayApplyAmount;
	}

	public void setTodayApplyAmount(BigDecimal todayApplyAmount) {
		this.todayApplyAmount = todayApplyAmount;
	}

	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public BigDecimal getDeferredAmount() {
		return deferredAmount;
	}

	public void setDeferredAmount(BigDecimal deferredAmount) {
		this.deferredAmount = deferredAmount;
	}

}
