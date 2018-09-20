package com.waben.stock.interfaces.commonapi.maike.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteRet {
	/**
	 * 报价时间
	 */
	@JsonProperty("OFFER_PRICE_DATE")
	private String offerPriceDate;
	/**
	 * 到期时间
	 */
	@JsonProperty("EXPIRE_DATE")
	private String expireDate;
	/**
	 * 股票代码
	 */
	@JsonProperty("SYMBOL")
	private String symbol;
	/**
	 * 股票名称
	 */
	@JsonProperty("SYMBOL_NAME")
	private String symbolName;
	/**
	 * 周期类型:例： 1m 表示 1 个月， 10d 表示 10 天
	 */
	@JsonProperty("OFFER_CYCLE")
	private String offerCycle;
	/**
	 * 买入方式： 权证比
	 */
	@JsonProperty("EXERCISE_PRICE")
	private BigDecimal exercisePrice;
	/**
	 * 对应管理费： 权利金
	 */
	@JsonProperty("QUOTATION")
	private String quotation;
	/**
	 * 期权类型（C:看涨， P:看跌）
	 * <p>
	 * 我们系统使用看涨
	 * </p>
	 */
	@JsonProperty("OPTION_TYPE")
	private String optionType;
	/**
	 * 行权方式（E： 欧式， A： 美式）
	 * <p>
	 * 我们系统使用美式期权
	 * </p>
	 */
	@JsonProperty("EXERCISE_MODE")
	private String exerciseMode;

	public String getOfferPriceDate() {
		return offerPriceDate;
	}

	public void setOfferPriceDate(String offerPriceDate) {
		this.offerPriceDate = offerPriceDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public String getOfferCycle() {
		return offerCycle;
	}

	public void setOfferCycle(String offerCycle) {
		this.offerCycle = offerCycle;
	}

	public BigDecimal getExercisePrice() {
		return exercisePrice;
	}

	public void setExercisePrice(BigDecimal exercisePrice) {
		this.exercisePrice = exercisePrice;
	}

	public String getQuotation() {
		return quotation;
	}

	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getExerciseMode() {
		return exerciseMode;
	}

	public void setExerciseMode(String exerciseMode) {
		this.exerciseMode = exerciseMode;
	}

}
