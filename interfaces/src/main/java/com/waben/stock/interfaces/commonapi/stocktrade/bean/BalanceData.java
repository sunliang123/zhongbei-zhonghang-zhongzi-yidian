package com.waben.stock.interfaces.commonapi.stocktrade.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 账户信息
 * 
 * @author luomengan
 *
 */
public class BalanceData {

	/**
	 * 币种信息
	 * <ul>
	 * <li>CNY人民币</li>
	 * <li>USD美元</li>
	 * <li>HKD港币</li>
	 * </ul>
	 */
	@JsonProperty("money_type")
	private String moneyType;
	/**
	 * 当前余额
	 */
	@JsonProperty("current_balance")
	private BigDecimal currentBalance;
	/**
	 * 可用余额
	 */
	@JsonProperty("enable_balance")
	private BigDecimal enableBalance;
	/**
	 * 可取金额
	 */
	@JsonProperty("fetch_balance")
	private BigDecimal fetchBalance;
	/**
	 * 总资产
	 */
	@JsonProperty("asset_balance")
	private BigDecimal assetBalance;

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getEnableBalance() {
		return enableBalance;
	}

	public void setEnableBalance(BigDecimal enableBalance) {
		this.enableBalance = enableBalance;
	}

	public BigDecimal getFetchBalance() {
		return fetchBalance;
	}

	public void setFetchBalance(BigDecimal fetchBalance) {
		this.fetchBalance = fetchBalance;
	}

	public BigDecimal getAssetBalance() {
		return assetBalance;
	}

	public void setAssetBalance(BigDecimal assetBalance) {
		this.assetBalance = assetBalance;
	}

}
