package com.waben.stock.interfaces.dto.admin.stockoption;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.StockOptionTradeState;

public class StockOptionAdminDto {

	/**
	 * 交易ID
	 */
	private Long id;
	/**
	 * 交易单号
	 */
	private String tradeNo;
	/**
	 * 发布人姓名
	 * <p>
	 * 实名认证的姓名
	 * </p>
	 */
	private String publisherName;
	/**
	 * 发布人手机号
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
	 * 周期名称
	 */
	private String cycleName;
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
	 * 权利金比例(机构)
	 */
	private BigDecimal orgRightMoneyRatio;
	/**
	 * 权利金(机构)
	 */
	private BigDecimal orgRightMoney;
	/**
	 * 申购时间
	 */
	private Date applyTime;
	/**
	 * 可购股数
	 * <p>
	 * 仅在用户购买时计算，保存留作参考
	 * </p>
	 */
	private Integer numberOfStrand;
	/**
	 * 成交时间
	 */
	private Date buyingTime;

	/**
	 *
	 */
	private BigDecimal buyingLastPrice;

	/**
	 * 成交价格
	 */
	private BigDecimal buyingPrice;
	/**
	 * 用户申请行权时间
	 */
	private Date rightTime;
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
	 * 是否为测试单
	 */
	private Boolean isTest;
	/**
	 * 是否标记
	 */
	private Boolean isMark;
	/**
	 * 交易状态
	 */
	private StockOptionTradeState state;

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

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
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

	public BigDecimal getOrgRightMoneyRatio() {
		return orgRightMoneyRatio;
	}

	public void setOrgRightMoneyRatio(BigDecimal orgRightMoneyRatio) {
		this.orgRightMoneyRatio = orgRightMoneyRatio;
	}

	public BigDecimal getOrgRightMoney() {
		return orgRightMoney;
	}

	public void setOrgRightMoney(BigDecimal orgRightMoney) {
		this.orgRightMoney = orgRightMoney;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public StockOptionTradeState getState() {
		return state;
	}

	public void setState(StockOptionTradeState state) {
		this.state = state;
	}

	public Boolean getIsMark() {
		return isMark;
	}

	public void setIsMark(Boolean isMark) {
		this.isMark = isMark;
	}

	public Date getRightTime() {
		return rightTime;
	}

	public void setRightTime(Date rightTime) {
		this.rightTime = rightTime;
	}

	public Integer getNumberOfStrand() {
		return numberOfStrand;
	}

	public void setNumberOfStrand(Integer numberOfStrand) {
		this.numberOfStrand = numberOfStrand;
	}

	public BigDecimal getBuyingLastPrice() {
		return buyingLastPrice;
	}

	public void setBuyingLastPrice(BigDecimal buyingLastPrice) {
		this.buyingLastPrice = buyingLastPrice;
	}
}
