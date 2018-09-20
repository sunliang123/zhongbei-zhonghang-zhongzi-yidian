package com.waben.stock.interfaces.commonapi.retrivestock.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 股票行情
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockMarket {

	private String exchangeId;
	private String instrumentId;
	private String name;
	/**
	 * 0停牌，1正常
	 */
	private int status;
	private long upTime;
	private String upTimeFormat;
	private BigDecimal upLimitPrice;
	private BigDecimal downLimitPrice;
	/**
	 * 交易日，格式yyyy-MM-dd
	 */
	private String tradeDay;
	/**
	 * 最新价
	 */
	private BigDecimal lastPrice;
	/**
	 * 跌涨价格
	 */
	private BigDecimal upDropPrice;
	/**
	 * 跌涨幅度
	 */
	private BigDecimal upDropSpeed;
	/**
	 * 最高价
	 */
	private BigDecimal highestPrice;
	/**
	 * 最低价
	 */
	private BigDecimal lowestPrice;
	/**
	 * 今开
	 */
	private BigDecimal openPrice;
	/**
	 * 昨收
	 */
	private BigDecimal preClsPrice;
	/**
	 * 成交量，单位手
	 */
	private BigDecimal volume;
	/**
	 * 成交额，单位元
	 */
	private BigDecimal turnover;
	/**
	 * 卖1~卖5
	 */
	private BigDecimal askPrice;
	private BigDecimal askPrice2;
	private BigDecimal askPrice3;
	private BigDecimal askPrice4;
	private BigDecimal askPrice5;
	private BigDecimal askVolume;
	private BigDecimal askVolume2;
	private BigDecimal askVolume3;
	private BigDecimal askVolume4;
	private BigDecimal askVolume5;
	/**
	 * 买1~买5
	 */
	private BigDecimal bidPrice;
	private BigDecimal bidPrice2;
	private BigDecimal bidPrice3;
	private BigDecimal bidPrice4;
	private BigDecimal bidPrice5;
	private BigDecimal bidVolume;
	private BigDecimal bidVolume2;
	private BigDecimal bidVolume3;
	private BigDecimal bidVolume4;
	private BigDecimal bidVolume5;

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

	public BigDecimal getAskPrice2() {
		return askPrice2;
	}

	public void setAskPrice2(BigDecimal askPrice2) {
		this.askPrice2 = askPrice2;
	}

	public BigDecimal getAskPrice3() {
		return askPrice3;
	}

	public void setAskPrice3(BigDecimal askPrice3) {
		this.askPrice3 = askPrice3;
	}

	public BigDecimal getAskPrice4() {
		return askPrice4;
	}

	public void setAskPrice4(BigDecimal askPrice4) {
		this.askPrice4 = askPrice4;
	}

	public BigDecimal getAskPrice5() {
		return askPrice5;
	}

	public void setAskPrice5(BigDecimal askPrice5) {
		this.askPrice5 = askPrice5;
	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	public BigDecimal getBidPrice2() {
		return bidPrice2;
	}

	public void setBidPrice2(BigDecimal bidPrice2) {
		this.bidPrice2 = bidPrice2;
	}

	public BigDecimal getBidPrice3() {
		return bidPrice3;
	}

	public void setBidPrice3(BigDecimal bidPrice3) {
		this.bidPrice3 = bidPrice3;
	}

	public BigDecimal getBidPrice4() {
		return bidPrice4;
	}

	public void setBidPrice4(BigDecimal bidPrice4) {
		this.bidPrice4 = bidPrice4;
	}

	public BigDecimal getBidPrice5() {
		return bidPrice5;
	}

	public void setBidPrice5(BigDecimal bidPrice5) {
		this.bidPrice5 = bidPrice5;
	}

	public BigDecimal getAskVolume() {
		return askVolume;
	}

	public void setAskVolume(BigDecimal askVolume) {
		this.askVolume = askVolume;
	}

	public BigDecimal getAskVolume2() {
		return askVolume2;
	}

	public void setAskVolume2(BigDecimal askVolume2) {
		this.askVolume2 = askVolume2;
	}

	public BigDecimal getAskVolume3() {
		return askVolume3;
	}

	public void setAskVolume3(BigDecimal askVolume3) {
		this.askVolume3 = askVolume3;
	}

	public BigDecimal getAskVolume4() {
		return askVolume4;
	}

	public void setAskVolume4(BigDecimal askVolume4) {
		this.askVolume4 = askVolume4;
	}

	public BigDecimal getAskVolume5() {
		return askVolume5;
	}

	public void setAskVolume5(BigDecimal askVolume5) {
		this.askVolume5 = askVolume5;
	}

	public BigDecimal getBidVolume() {
		return bidVolume;
	}

	public void setBidVolume(BigDecimal bidVolume) {
		this.bidVolume = bidVolume;
	}

	public BigDecimal getBidVolume2() {
		return bidVolume2;
	}

	public void setBidVolume2(BigDecimal bidVolume2) {
		this.bidVolume2 = bidVolume2;
	}

	public BigDecimal getBidVolume3() {
		return bidVolume3;
	}

	public void setBidVolume3(BigDecimal bidVolume3) {
		this.bidVolume3 = bidVolume3;
	}

	public BigDecimal getBidVolume4() {
		return bidVolume4;
	}

	public void setBidVolume4(BigDecimal bidVolume4) {
		this.bidVolume4 = bidVolume4;
	}

	public BigDecimal getBidVolume5() {
		return bidVolume5;
	}

	public void setBidVolume5(BigDecimal bidVolume5) {
		this.bidVolume5 = bidVolume5;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getPreClsPrice() {
		return preClsPrice;
	}

	public void setPreClsPrice(BigDecimal preClsPrice) {
		this.preClsPrice = preClsPrice;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getTurnover() {
		return turnover;
	}

	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}

}
