package com.waben.stock.applayer.tactics.dto.publisher;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty(value = "持仓中的盈亏")
	private BigDecimal holdProfitOrLoss;
	/**
	 * 总共点买的申请资金，统计所有的记录
	 */
	@ApiModelProperty(value = "总点买")
	private BigDecimal totalApplyAmount;
	/**
	 * 今天点买的申请资金，统计今天的记录
	 */
	@ApiModelProperty(value = "今日买入")
	private BigDecimal todayApplyAmount;
	/**
	 * 持仓中的冻结资金，统计持仓中的记录
	 */
	@ApiModelProperty(value = "履约保证金")
	private BigDecimal frozenAmount;
	/**
	 * 持仓中的递延费，统计持仓中的记录
	 */
	@ApiModelProperty(value = "持仓递延费")
	private BigDecimal deferredAmount = new BigDecimal(0);
	/**
	 * 总名义本金
	 */
	@ApiModelProperty(value = "总名义本金")
	private BigDecimal totalNominalAmount;
	/**
	 * 今日申购
	 */
	@ApiModelProperty(value = "今日申购")
	private BigDecimal todayNominalAmount;
	/**
	 * 总权利金
	 */
	@ApiModelProperty(value = "总权利金")
	private BigDecimal totalRightMoney;
	/**
	 * 今日权利金
	 */
	@ApiModelProperty(value = "今日权利金")
	private BigDecimal todayRightMoney;

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

	public BigDecimal getTotalNominalAmount() {
		return totalNominalAmount;
	}

	public void setTotalNominalAmount(BigDecimal totalNominalAmount) {
		this.totalNominalAmount = totalNominalAmount;
	}

	public BigDecimal getTodayNominalAmount() {
		return todayNominalAmount;
	}

	public void setTodayNominalAmount(BigDecimal todayNominalAmount) {
		this.todayNominalAmount = todayNominalAmount;
	}

	public BigDecimal getTotalRightMoney() {
		return totalRightMoney;
	}

	public void setTotalRightMoney(BigDecimal totalRightMoney) {
		this.totalRightMoney = totalRightMoney;
	}

	public BigDecimal getTodayRightMoney() {
		return todayRightMoney;
	}

	public void setTodayRightMoney(BigDecimal todayRightMoney) {
		this.todayRightMoney = todayRightMoney;
	}

}
