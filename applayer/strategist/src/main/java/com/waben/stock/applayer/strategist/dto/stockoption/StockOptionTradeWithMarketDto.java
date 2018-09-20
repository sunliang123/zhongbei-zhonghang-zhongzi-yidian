package com.waben.stock.applayer.strategist.dto.stockoption;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;

/**
 * 期权交易+股票行情信息
 * 
 * @author luomengan
 *
 */
public class StockOptionTradeWithMarketDto extends StockOptionTradeDto {

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
	 * 0停牌，1正常
	 */
	private Integer stockStatus;
	/**
	 * 是否可以申请行权
	 */
	private boolean canBeRight;

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

	public BigDecimal getProfit() {
		BigDecimal profit = super.getProfit();
		if (profit == null && getLastPrice() != null && getBuyingPrice() != null) {
			if (getLastPrice().compareTo(getBuyingPrice()) > 0) {
				return getLastPrice().subtract(getBuyingPrice()).divide(getBuyingPrice(), 10, RoundingMode.DOWN)
						.multiply(getNominalAmount()).setScale(2, RoundingMode.HALF_EVEN);
			}
		}
		return profit != null ? profit : new BigDecimal(0);
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public boolean isCanBeRight() {
		return canBeRight;
	}

	public void setCanBeRight(boolean canBeRight) {
		this.canBeRight = canBeRight;
	}

}
