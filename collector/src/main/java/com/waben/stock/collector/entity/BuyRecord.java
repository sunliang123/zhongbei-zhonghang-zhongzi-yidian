package com.waben.stock.collector.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 点买记录
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_buy_record")
public class BuyRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
	/**
	 * 系列号
	 */
	@Column(name = "serial_code")
	private String serialCode;
	/**
	 * 交易编号
	 */
	@Column(name = "trade_no")
	private String tradeNo;
	/**
	 * 申请资金
	 */
	@Column(name = "apply_amount")
	private BigDecimal applyAmount;
	/**
	 * 信息服务费
	 */
	@Column(name = "service_fee")
	private BigDecimal serviceFee;
	/**
	 * 保证金
	 */
	@Column(name = "reserve_fund")
	private BigDecimal reserveFund;
	/**
	 * 是否递延
	 */
	@Column(name = "deferred")
	private Boolean deferred;
	/**
	 * 递延费
	 */
	@Column(name = "deferred_fee")
	private BigDecimal deferredFee;
	/**
	 * 止盈点
	 */
	@Column(name = "profit_point", scale = 3)
	private BigDecimal profitPoint;
	/**
	 * 止盈预警点位
	 */
	@Column(name = "profit_warn_position")
	private BigDecimal profitWarnPosition;
	/**
	 * 止盈点位
	 */
	@Column(name = "profit_position")
	private BigDecimal profitPosition;
	/**
	 * 止损点
	 */
	@Column(name = "loss_point", scale = 3)
	private BigDecimal lossPoint;
	/**
	 * 杠杆倍数
	 */
	@Column(name = "loss_multiple")
	private Integer lossMultiple;
	/**
	 * 止损预警点位
	 */
	@Column(name = "loss_warn_position")
	private BigDecimal lossWarnPosition;
	/**
	 * 止损点位
	 */
	@Column(name = "loss_position")
	private BigDecimal lossPosition;
	/**
	 * 状态
	 */
	@Column(name = "state")
	private Integer state;
	/**
	 * 持股数
	 */
	@Column(name = "number_of_strand")
	private Integer numberOfStrand;
	/**
	 * 委托价格
	 */
	@Column(name = "delegate_price")
	private BigDecimal delegatePrice;
	/**
	 * 委托编号，证券账号购买股票后的交易编号
	 */
	@Column(name = "delegate_number")
	private String delegateNumber;
	/**
	 * 点买时间
	 */
	@Column(name = "buying_time")
	private Date buyingTime;
	/**
	 * 买入价格
	 */
	@Column(name = "buying_price")
	private BigDecimal buyingPrice;
	/**
	 * 到期时间
	 */
	@Column(name = "expire_time")
	private Date expireTime;
	/**
	 * 风控类型
	 */
	@Column(name = "wind_control_type")
	private Integer windControlType;
	/**
	 * 卖出时间
	 */
	@Column(name = "selling_time")
	private Date sellingTime;
	/**
	 * 卖出价格
	 */
	@Column(name = "selling_price")
	private BigDecimal sellingPrice;
	/**
	 * 股票代码
	 */
	@Column(name = "stock_code")
	private String stockCode;
	/**
	 * 股票名称
	 */
	@Column(name = "stock_name")
	private String stockName;
	/**
	 * 策略类型ID
	 */
	@Column(name = "strategy_type_id")
	private Long strategyTypeId;
	/**
	 * 投资人ID
	 */
	@Column(name = "investor_id")
	private Long investorId;
	/**
	 * 投资人名称
	 */
	@Column(name = "investor_name")
	private String investorName;
	/**
	 * 发布人ID
	 */
	@Column(name = "publisher_id")
	private Long publisherId;
	/**
	 * 发布人序列号
	 */
	@Column(name = "publisher_serial_code")
	private String publisherSerialCode;
	/**
	 * 发布人手机号
	 */
	@Column(name = "publisher_phone")
	private String publisherPhone;
	/**
	 * 点买记录创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 点买记录更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

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

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public BigDecimal getReserveFund() {
		return reserveFund;
	}

	public void setReserveFund(BigDecimal reserveFund) {
		this.reserveFund = reserveFund;
	}

	public Boolean getDeferred() {
		return deferred;
	}

	public void setDeferred(Boolean deferred) {
		this.deferred = deferred;
	}

	public BigDecimal getProfitPoint() {
		return profitPoint;
	}

	public void setProfitPoint(BigDecimal profitPoint) {
		this.profitPoint = profitPoint;
	}

	public BigDecimal getProfitWarnPosition() {
		return profitWarnPosition;
	}

	public void setProfitWarnPosition(BigDecimal profitWarnPosition) {
		this.profitWarnPosition = profitWarnPosition;
	}

	public BigDecimal getProfitPosition() {
		return profitPosition;
	}

	public void setProfitPosition(BigDecimal profitPosition) {
		this.profitPosition = profitPosition;
	}

	public BigDecimal getLossPoint() {
		return lossPoint;
	}

	public void setLossPoint(BigDecimal lossPoint) {
		this.lossPoint = lossPoint;
	}

	public BigDecimal getLossWarnPosition() {
		return lossWarnPosition;
	}

	public void setLossWarnPosition(BigDecimal lossWarnPosition) {
		this.lossWarnPosition = lossWarnPosition;
	}

	public BigDecimal getLossPosition() {
		return lossPosition;
	}

	public void setLossPosition(BigDecimal lossPosition) {
		this.lossPosition = lossPosition;
	}

	public Integer getNumberOfStrand() {
		return numberOfStrand;
	}

	public void setNumberOfStrand(Integer numberOfStrand) {
		this.numberOfStrand = numberOfStrand;
	}

	public String getDelegateNumber() {
		return delegateNumber;
	}

	public void setDelegateNumber(String delegateNumber) {
		this.delegateNumber = delegateNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Long getStrategyTypeId() {
		return strategyTypeId;
	}

	public void setStrategyTypeId(Long strategyTypeId) {
		this.strategyTypeId = strategyTypeId;
	}

	public Long getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherSerialCode() {
		return publisherSerialCode;
	}

	public void setPublisherSerialCode(String publisherSerialCode) {
		this.publisherSerialCode = publisherSerialCode;
	}

	public BigDecimal getDelegatePrice() {
		return delegatePrice;
	}

	public void setDelegatePrice(BigDecimal delegatePrice) {
		this.delegatePrice = delegatePrice;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public BigDecimal getDeferredFee() {
		return deferredFee;
	}

	public void setDeferredFee(BigDecimal deferredFee) {
		this.deferredFee = deferredFee;
	}

	public Integer getLossMultiple() {
		return lossMultiple;
	}

	public void setLossMultiple(Integer lossMultiple) {
		this.lossMultiple = lossMultiple;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getWindControlType() {
		return windControlType;
	}

	public void setWindControlType(Integer windControlType) {
		this.windControlType = windControlType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

}
