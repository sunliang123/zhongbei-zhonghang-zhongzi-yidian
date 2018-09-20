package com.waben.stock.applayer.strategist.dto.stockcontent;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 股票盘口
 * 
 * @author luomengan
 *
 */
public class StockDiscDto {

	/**
	 * 股票代码
	 */
	private String code;
	/**
	 * 股票民称
	 */
	private String name;
	/**
	 * 今开
	 */
	private BigDecimal todayOpenPrice;
	/**
	 * 昨收
	 */
	private BigDecimal yesterdayClosePrice;
	/**
	 * 最高价
	 */
	private BigDecimal highestPrice;
	/**
	 * 最低价
	 */
	private BigDecimal lowestPrice;
	/**
	 * 跌涨价
	 */
	private BigDecimal upDropPrice;
	/**
	 * 跌涨幅
	 */
	private BigDecimal upDropSpeed;

	@SuppressWarnings("unused")
	private BigDecimal riseStopPrice;

	@SuppressWarnings("unused")
	private BigDecimal fallStopPrice;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getYesterdayClosePrice() {
		return yesterdayClosePrice;
	}

	public void setYesterdayClosePrice(BigDecimal yesterdayClosePrice) {
		this.yesterdayClosePrice = yesterdayClosePrice;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
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

	public BigDecimal getTodayOpenPrice() {
		return todayOpenPrice;
	}

	public void setTodayOpenPrice(BigDecimal todayOpenPrice) {
		this.todayOpenPrice = todayOpenPrice;
	}

	public BigDecimal getRiseStopPrice() {
		if (yesterdayClosePrice == null) {
			return null;
		}
		return yesterdayClosePrice.multiply(new BigDecimal(1.1)).setScale(2, RoundingMode.DOWN);
	}

	public BigDecimal getFallStopPrice() {
		if (yesterdayClosePrice == null) {
			return null;
		}
		return yesterdayClosePrice.multiply(new BigDecimal(0.9)).setScale(2, RoundingMode.DOWN);
	}

}
