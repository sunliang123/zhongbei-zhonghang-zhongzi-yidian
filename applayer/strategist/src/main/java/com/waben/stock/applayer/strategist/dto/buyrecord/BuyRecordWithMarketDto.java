package com.waben.stock.applayer.strategist.dto.buyrecord;

import java.math.BigDecimal;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;

/**
 * 点买记录+股票行情信息
 * 
 * @author luomengan
 *
 */
public class BuyRecordWithMarketDto extends BuyRecordDto {

	/**
	 * 盈亏
	 */
	private BigDecimal profitOrLoss;
	/**
	 * 发布人盈亏
	 */
	private BigDecimal publisherProfitOrLoss;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 最新价
	 */
	private BigDecimal lastPrice;
	/**
	 * 涨跌价格
	 */
	private BigDecimal upDropPrice;
	/**
	 * 涨跌幅度
	 */
	private BigDecimal upDropSpeed;
	/**
	 * 递延天数
	 */
	private Integer deferredDays;
	/**
	 * 递延费
	 */
	private BigDecimal deferredCharges;
	/**
	 * 周期
	 */
	private Integer cycle;
	/**
	 * 0停牌，1正常
	 */
	private Integer stockStatus;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigDecimal getUpDropPrice() {
		return upDropPrice;
	}

	public void setUpDropPrice(BigDecimal upDropPrice) {
		this.upDropPrice = upDropPrice;
	}

	public BigDecimal getUpDropSpeed() {
		return upDropSpeed;
	}

	public void setUpDropSpeed(BigDecimal upDropSpeed) {
		this.upDropSpeed = upDropSpeed;
	}

	public BigDecimal getPublisherProfitOrLoss() {
		return publisherProfitOrLoss;
	}

	public void setPublisherProfitOrLoss(BigDecimal publisherProfitOrLoss) {
		this.publisherProfitOrLoss = publisherProfitOrLoss;
	}

	public void setProfitOrLoss(BigDecimal profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}

	public BigDecimal getProfitOrLoss() {
		if (profitOrLoss == null && getLastPrice() != null && getBuyingPrice() != null) {
			return new BigDecimal(getNumberOfStrand()).multiply(getLastPrice().subtract(getBuyingPrice()));
		}
		return profitOrLoss != null ? profitOrLoss : new BigDecimal(0);
	}

	public Integer getDeferredDays() {
		if (deferredDays == null) {
			return 0;
		}
		return deferredDays;
	}

	public void setDeferredDays(Integer deferredDays) {
		this.deferredDays = deferredDays;
	}

	public BigDecimal getDeferredCharges() {
		if (deferredCharges == null) {
			return new BigDecimal(0);
		}
		return deferredCharges;
	}

	public void setDeferredCharges(BigDecimal deferredCharges) {
		this.deferredCharges = deferredCharges;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

}
