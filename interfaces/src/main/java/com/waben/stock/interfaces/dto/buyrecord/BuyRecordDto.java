package com.waben.stock.interfaces.dto.buyrecord;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.enums.WindControlType;

/**
 * 点买记录
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyRecordDto {

	private Long id;
	/**
	 * 系列号
	 */
	private String serialCode;
	/**
	 * 交易编号
	 */
	private String tradeNo;
	/**
	 * 申请资金
	 */
	private BigDecimal applyAmount;
	/**
	 * 信息服务费
	 */
	private BigDecimal serviceFee;
	/**
	 * 保证金
	 */
	private BigDecimal reserveFund;
	/**
	 * 是否递延费
	 */
	private Boolean deferred;
	/**
	 * 递延费
	 */
	private BigDecimal deferredFee;
	/**
	 * 止盈点
	 */
	private BigDecimal profitPoint;
	/**
	 * 止盈预警点位
	 */
	private BigDecimal profitWarnPosition;
	/**
	 * 止盈点位
	 */
	private BigDecimal profitPosition;
	/**
	 * 止损点
	 */
	private BigDecimal lossPoint;
	/**
	 * 杠杆倍数
	 */
	private Integer lossMultiple;
	/**
	 * 止损预警点位
	 */
	private BigDecimal lossWarnPosition;
	/**
	 * 止损点位
	 */
	private BigDecimal lossPosition;
	/**
	 * 状态
	 */
	private BuyRecordState state;
	/**
	 * 持股数
	 */
	private Integer numberOfStrand;
	/**
	 * 委托价格
	 */
	private BigDecimal delegatePrice;
	/**
	 * 委托编号，证券账号购买股票后的交易编号
	 */
	private String delegateNumber;
	/**
	 * 点买记录创建时间
	 */
	private Date createTime;
	private Date expireTime;
	/**
	 * 点买记录更新时间
	 */
	private Date updateTime;
	/**
	 * 点买时间
	 */
	private Date buyingTime;
	/**
	 * 买入价格
	 */
	private BigDecimal buyingPrice;
	/**
	 * 风控类型
	 */
	private WindControlType windControlType;
	/**
	 * 卖出时间
	 */
	private Date sellingTime;
	/**
	 * 卖出价格
	 */
	private BigDecimal sellingPrice;
	/**
	 * 盈亏
	 */
	private BigDecimal profitOrLoss;
	/**
	 * 发布人盈亏
	 */
	private BigDecimal publisherProfitOrLoss;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 策略类型ID
	 */
	private Long strategyTypeId;
	/**
	 * 投资人ID
	 */
	private Long investorId;

	private String investorName;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 发布人序列号
	 */
	private String publisherSerialCode;

	private String stockName;

	private String publisherPhone;

	private PublisherDto publisherDto;

	private StockDto stockDto;
	private SettlementDto settlement;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public BuyRecordState getState() {
		return state;
	}

	public void setState(BuyRecordState state) {
		this.state = state;
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

	public WindControlType getWindControlType() {
		return windControlType;
	}

	public String getWindControlTypes() {
		return windControlType != null ? windControlType.getType() : null;
	}

	public void setWindControlType(WindControlType windControlType) {
		this.windControlType = windControlType;
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

	public StockDto getStockDto() {
		return stockDto;
	}

	public void setStockDto(StockDto stockDto) {
		this.stockDto = stockDto;
	}

	public BigDecimal getDelegatePrice() {
		return delegatePrice;
	}

	public void setDelegatePrice(BigDecimal delegatePrice) {
		this.delegatePrice = delegatePrice;
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

	public PublisherDto getPublisherDto() {
		return publisherDto;
	}

	public void setPublisherDto(PublisherDto publisherDto) {
		this.publisherDto = publisherDto;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getBuyRecordState() {
		return state != null ? state.getStatus() : null;
	}

	public String getDeferredStatus() {
		return deferred == true ? "是" : "否";
	}

	public BigDecimal getDeferredFee() {
		return deferredFee;
	}

	public void setDeferredFee(BigDecimal deferredFee) {
		this.deferredFee = deferredFee;
	}

	public String getProfitPointPercent() {
		return profitPoint.multiply(new BigDecimal(100)).setScale(2).toString() + "%";
	}

	public String getLossPointPercent() {
		return lossPoint.multiply(new BigDecimal(100)).setScale(2).toString() + "%";
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public BigDecimal getProfitOrLoss() {
		return profitOrLoss;
	}

	public void setProfitOrLoss(BigDecimal profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}

	public BigDecimal getPublisherProfitOrLoss() {
		return publisherProfitOrLoss;
	}

	public void setPublisherProfitOrLoss(BigDecimal publisherProfitOrLoss) {
		this.publisherProfitOrLoss = publisherProfitOrLoss;
	}

	public Integer getLossMultiple() {
		return lossMultiple;
	}

	public void setLossMultiple(Integer lossMultiple) {
		this.lossMultiple = lossMultiple;
	}

	public SettlementDto getSettlement() {
		return settlement;
	}

	public void setSettlement(SettlementDto settlement) {
		this.settlement = settlement;
	}
}
