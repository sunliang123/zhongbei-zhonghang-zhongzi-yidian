package com.waben.stock.datalayer.stockoption.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.waben.stock.datalayer.stockoption.entity.enumconverter.OfflineStockOptionTradeStateConverter;
import com.waben.stock.datalayer.stockoption.entity.enumconverter.StockOptionBuyingTypeConverter;
import com.waben.stock.datalayer.stockoption.entity.enumconverter.StockOptionTradeStateConverter;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;
import com.waben.stock.interfaces.enums.StockOptionBuyingType;
import com.waben.stock.interfaces.enums.StockOptionTradeState;

/**
 * 用户股票期权交易信息
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "stock_option_trade")
public class StockOptionTrade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 交易单号
	 */
	private String tradeNo;
	/**
	 * 交易状态
	 */
	@Convert(converter = StockOptionTradeStateConverter.class)
	private StockOptionTradeState state;
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
	@Column(scale = 4)
	private BigDecimal rightMoneyRatio;
	/**
	 * 权利金
	 */
	private BigDecimal rightMoney;
	/**
	 * 周期ID
	 */
	private Long cycleId;
	/**
	 * 周期
	 */
	private Integer cycle;
	/**
	 * 周期月数
	 */
	private String cycleMonth;
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
	@Convert(converter = StockOptionBuyingTypeConverter.class)
	private StockOptionBuyingType buyingType;
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
	 * 买入时最新价格
	 */
	private BigDecimal buyingLastPrice;
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
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否为测试单
	 */
	private Boolean isTest;
	/**
	 * 是否标记
	 */
	private Boolean isMark;
	/**
	 * 对应的线下期权交易信息
	 */
	@OneToOne
	@JoinColumn(name = "offline_trade")
	private OfflineStockOptionTrade offlineTrade;
	/**
	 * 线下期权交易状态
	 */
	@Convert(converter = OfflineStockOptionTradeStateConverter.class)
	private OfflineStockOptionTradeState status;
	/**
	 * 推广代理商ID
	 */
	private Long promotionOrgId;

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

	public StockOptionTradeState getState() {
		return state;
	}

	public void setState(StockOptionTradeState state) {
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

	public StockOptionBuyingType getBuyingType() {
		return buyingType;
	}

	public void setBuyingType(StockOptionBuyingType buyingType) {
		this.buyingType = buyingType;
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

	public OfflineStockOptionTrade getOfflineTrade() {
		return offlineTrade;
	}

	public void setOfflineTrade(OfflineStockOptionTrade offlineTrade) {
		this.offlineTrade = offlineTrade;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getCycleMonth() {
		return cycleMonth;
	}

	public void setCycleMonth(String cycleMonth) {
		this.cycleMonth = cycleMonth;
	}

	public OfflineStockOptionTradeState getStatus() {
		return status;
	}

	public void setStatus(OfflineStockOptionTradeState status) {
		this.status = status;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public Boolean getIsMark() {
		return isMark;
	}

	public void setIsMark(Boolean isMark) {
		this.isMark = isMark;
	}

	public Long getPromotionOrgId() {
		return promotionOrgId;
	}

	public void setPromotionOrgId(Long promotionOrgId) {
		this.promotionOrgId = promotionOrgId;
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
