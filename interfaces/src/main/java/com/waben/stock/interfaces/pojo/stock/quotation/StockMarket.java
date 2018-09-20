package com.waben.stock.interfaces.pojo.stock.quotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * 股票行情
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockMarket {
	//交易单号
	private String exchangeId;
	//股票代码
	private String instrumentId;
//	最新价格
	private BigDecimal lastPrice;
//	股票名称
	private String name;
//
	private int status;
//	交易日
	private String tradeDay;
//  涨跌价格
	private BigDecimal upDropPrice;
//	涨跌速度
	private BigDecimal upDropSpeed;
//	涨跌时间
	private long upTime;
	private String upTimeFormat;
//	最高价格
	private BigDecimal highestPrice;
//	最低价格
	private BigDecimal lowestPrice;
//	卖方开价
	private BigDecimal askPrice;
//	涨停价格
	private BigDecimal upLimitPrice;
//	跌停价格
	private BigDecimal downLimitPrice;

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTradeDay() {
		return tradeDay;
	}

	public void setTradeDay(String tradeDay) {
		this.tradeDay = tradeDay;
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

	public long getUpTime() {
		return upTime;
	}

	public void setUpTime(long upTime) {
		this.upTime = upTime;
	}

	public String getUpTimeFormat() {
		return upTimeFormat;
	}

	public void setUpTimeFormat(String upTimeFormat) {
		this.upTimeFormat = upTimeFormat;
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

	public BigDecimal getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}

	public BigDecimal getUpLimitPrice() {
		return upLimitPrice;
	}

	public void setUpLimitPrice(BigDecimal upLimitPrice) {
		this.upLimitPrice = upLimitPrice;
	}

	public BigDecimal getDownLimitPrice() {
		return downLimitPrice;
	}

	public void setDownLimitPrice(BigDecimal downLimitPrice) {
		this.downLimitPrice = downLimitPrice;
	}
}
