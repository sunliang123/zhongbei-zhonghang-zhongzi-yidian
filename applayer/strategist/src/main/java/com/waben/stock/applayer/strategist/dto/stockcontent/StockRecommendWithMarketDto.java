package com.waben.stock.applayer.strategist.dto.stockcontent;

import java.math.BigDecimal;

import com.waben.stock.interfaces.dto.stockcontent.StockRecommendDto;

/**
 * 推荐股票和股票行情
 * 
 * @author luomengan
 *
 */
public class StockRecommendWithMarketDto extends StockRecommendDto {

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

}
