package com.waben.stock.applayer.tactics.dto.system;

import java.math.BigDecimal;

/**
 * 股票市场指数
 * 
 * @author luomengan
 *
 */
public class StockMarketExponentDto {

	/**
	 * 指数名称
	 */
	private String name;
	/**
	 * 指数代码
	 */
	private String code;
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

	public StockMarketExponentDto(String name, String code, BigDecimal lastPrice, BigDecimal upDropPrice,
			BigDecimal upDropSpeed) {
		super();
		this.name = name;
		this.code = code;
		this.lastPrice = lastPrice;
		this.upDropPrice = upDropPrice;
		this.upDropSpeed = upDropSpeed;
	}

	public StockMarketExponentDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public BigDecimal getUpDropPrice() {
		return upDropPrice;
	}

	public void setUpDropPrice(BigDecimal upDropPrice) {
		this.upDropPrice = upDropPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public BigDecimal getUpDropSpeed() {
		return upDropSpeed;
	}

	public void setUpDropSpeed(BigDecimal upDropSpeed) {
		this.upDropSpeed = upDropSpeed.setScale(4, BigDecimal.ROUND_HALF_EVEN);
	}

}
