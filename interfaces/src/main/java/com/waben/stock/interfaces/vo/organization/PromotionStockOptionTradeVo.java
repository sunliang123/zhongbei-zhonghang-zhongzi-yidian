package com.waben.stock.interfaces.vo.organization;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class PromotionStockOptionTradeVo {

	/**
	 * 点买记录ID
	 */
	private Long tradeId;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 发布人手机号码
	 */
	private String publisherPhone;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 期权周期名称
	 */
	private String cycleName;
	/**
	 * 策略状态
	 */
	private Integer state;
	/**
	 * 名义本金
	 */
	private BigDecimal nominalAmount;
	/**
	 * 权利金
	 */
	private BigDecimal rightMoney;
	/**
	 * 点买时间
	 */
	private Date buyingTime;
	/**
	 * 买入价格
	 */
	private BigDecimal buyingPrice;
	/**
	 * 卖出时间
	 */
	private Date sellingTime;
	/**
	 * 卖出价格
	 */
	private BigDecimal sellingPrice;
	/**
	 * 当前价格
	 */
	private BigDecimal lastPrice;
	/**
	 * 盈亏
	 */
	private BigDecimal profit;
	/**
	 * 所属机构代码
	 */
	private String orgCode;
	/**
	 * 所属机构名称
	 */
	private String orgName;

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Date getBuyingTime() {
		return buyingTime;
	}

	public void setBuyingTime(Date buyingTime) {
		this.buyingTime = buyingTime;
	}

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Date getSellingTime() {
		return sellingTime;
	}

	public void setSellingTime(Date sellingTime) {
		this.sellingTime = sellingTime;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigDecimal getProfit() {
		if (profit == null) {
			if (buyingPrice != null) {
				if (sellingPrice != null) {
					return sellingPrice.subtract(buyingPrice).divide(buyingPrice, 10, RoundingMode.DOWN)
							.multiply(nominalAmount).setScale(2, RoundingMode.DOWN);
				} else {
					return lastPrice.subtract(buyingPrice).divide(buyingPrice, 10, RoundingMode.DOWN)
							.multiply(nominalAmount).setScale(2, RoundingMode.DOWN);
				}
			}
		}
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public BigDecimal getNominalAmount() {
		return nominalAmount;
	}

	public void setNominalAmount(BigDecimal nominalAmount) {
		this.nominalAmount = nominalAmount;
	}

	public BigDecimal getRightMoney() {
		return rightMoney;
	}

	public void setRightMoney(BigDecimal rightMoney) {
		this.rightMoney = rightMoney;
	}

}
