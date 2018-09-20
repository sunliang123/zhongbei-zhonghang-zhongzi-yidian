package com.waben.stock.interfaces.vo.stockoption;

import com.waben.stock.interfaces.dto.stockoption.OfflineStockOptionTradeDto;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;
import com.waben.stock.interfaces.enums.StockOptionBuyingType;
import com.waben.stock.interfaces.enums.StockOptionTradeState;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户股票期权申购信息
 * 
 * @author luomengan
 *
 */

public class StockOptionTradeVo{



	private Long id;
	/**
	 * 交易单号
	 */
	private String tradeNo;
	/**
	 * 交易状态
	 */
	private String state;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 名义本金
	 */
	private BigDecimal nominalAmount;
	/**
	 * 权利金比例
	 */
	private BigDecimal rightMoneyRatio;
	/**
	 * 权利金
	 */
	private BigDecimal rightMoney;
	/**
	 * 周期
	 */
	private Integer cycle;
	/**
	 * 周期名称
	 */
	private String cycleName;
	/**
	 * 到期时间
	 */
	private Date expireTime;
	/**
	 * 申购时间
	 */
	private Date applyTime;
	/**
	 * 买入方式
	 */
	private String buyingType;
	/**
	 * 成交时间
	 */
	private Date buyingTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 成交价格
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
	 * 行权时间
	 */
	private Date rightTime;
	/**
	 * 盈利
	 */
	private BigDecimal profit;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 发布人手机号码
	 */
	private String publisherPhone;
	/**
	 * 对应的线下期权交易信息
	 */
	private OfflineStockOptionTradeVo offlineTrade;

	private InquiryResultVo inquiryResultVo;

	private OfflineStockOptionTradeState status;

	private Boolean isTest;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getState() {
		return state;
	}

	public void setStates(String state) {
		this.state = state;
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

	public BigDecimal getNominalAmount() {
		return nominalAmount;
	}

	public void setNominalAmount(BigDecimal nominalAmount) {
		this.nominalAmount = nominalAmount;
	}

	public BigDecimal getRightMoneyRatio() {
		return rightMoneyRatio;
	}

	public void setRightMoneyRatio(BigDecimal rightMoneyRatio) {
		this.rightMoneyRatio = rightMoneyRatio;
	}

	public BigDecimal getRightMoney() {
		return rightMoney;
	}

	public void setRightMoney(BigDecimal rightMoney) {
		this.rightMoney = rightMoney;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getBuyingType() {
		return buyingType;
	}

	public void setBuyingTypes(String buyingType) {
		this.buyingType = buyingType;
	}

	public Date getBuyingTime() {
		return buyingTime;
	}

	public void setBuyingTime(Date buyingTime) {
		this.buyingTime = buyingTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getRightTime() {
		return rightTime;
	}

	public void setRightTime(Date rightTime) {
		this.rightTime = rightTime;
	}

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

	public OfflineStockOptionTradeVo getOfflineTrade() {
		return offlineTrade;
	}

	public void setOfflineTrade(OfflineStockOptionTradeVo offlineTrade) {
		this.offlineTrade = offlineTrade;
	}

	public InquiryResultVo getInquiryResultVo() {
		return inquiryResultVo;
	}

	public void setInquiryResultVo(InquiryResultVo inquiryResultVo) {
		this.inquiryResultVo = inquiryResultVo;
	}

	public OfflineStockOptionTradeState getStatus() {
		return status;
	}

	public void setStatus(OfflineStockOptionTradeState status) {
		this.status = status;
	}

	public Boolean getTest() {
		return isTest;
	}

	public void setTest(Boolean test) {
		isTest = test;
	}
}
