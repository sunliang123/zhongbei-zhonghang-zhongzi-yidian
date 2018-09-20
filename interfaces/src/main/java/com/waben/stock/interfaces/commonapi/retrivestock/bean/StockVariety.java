package com.waben.stock.interfaces.commonapi.retrivestock.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 股票品种
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockVariety {

	/**
	 * 开市时间
	 */
	private String displayMarketTimes;
	/**
	 * 品种类型exponent指数
	 * <p>
	 * 如：shenzheng
	 * </p>
	 */
	private String smallVarietyTypeCode;
	/**
	 * 小数点位数
	 */
	private BigDecimal decimalScale;
	/**
	 * 股票品种代码
	 */
	private String varietyType;
	/**
	 * 股票品种名称
	 */
	private String varietyName;
	/**
	 * 大类别
	 * <p>
	 * 返回固定:stock
	 * </p>
	 */
	private String bigVarietyTypeCode;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 基线
	 */
	private Integer baseline;
	/**
	 * 交易市场ID
	 */
	private Integer exchangeId;
	/**
	 * 开市时间
	 */
	private String openMarketTime;
	/**
	 * 动态图表价格间隔
	 */
	private Integer flashChartPriceInterval;
	/**
	 * 品种ID
	 */
	private Integer varietyId;
	/**
	 * 交易所状态 0闭市 1开市
	 */
	private Boolean exchangeStatus;
	/**
	 * 小数点位数
	 */
	private Integer marketPoint;
	/**
	 * 交易所代码
	 * <ul>
	 * <li>4353 上海</li>
	 * <li>4609 深圳</li>
	 * <li>4621 创业板</li>
	 * </ul>
	 */
	private String exchangeCode;

	public String getDisplayMarketTimes() {
		return displayMarketTimes;
	}

	public void setDisplayMarketTimes(String displayMarketTimes) {
		this.displayMarketTimes = displayMarketTimes;
	}

	public String getSmallVarietyTypeCode() {
		return smallVarietyTypeCode;
	}

	public void setSmallVarietyTypeCode(String smallVarietyTypeCode) {
		this.smallVarietyTypeCode = smallVarietyTypeCode;
	}

	public BigDecimal getDecimalScale() {
		return decimalScale;
	}

	public void setDecimalScale(BigDecimal decimalScale) {
		this.decimalScale = decimalScale;
	}

	public String getVarietyType() {
		return varietyType;
	}

	public void setVarietyType(String varietyType) {
		this.varietyType = varietyType;
	}

	public String getBigVarietyTypeCode() {
		return bigVarietyTypeCode;
	}

	public void setBigVarietyTypeCode(String bigVarietyTypeCode) {
		this.bigVarietyTypeCode = bigVarietyTypeCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getBaseline() {
		return baseline;
	}

	public void setBaseline(Integer baseline) {
		this.baseline = baseline;
	}

	public Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getOpenMarketTime() {
		return openMarketTime;
	}

	public void setOpenMarketTime(String openMarketTime) {
		this.openMarketTime = openMarketTime;
	}

	public Integer getFlashChartPriceInterval() {
		return flashChartPriceInterval;
	}

	public void setFlashChartPriceInterval(Integer flashChartPriceInterval) {
		this.flashChartPriceInterval = flashChartPriceInterval;
	}

	public Integer getVarietyId() {
		return varietyId;
	}

	public void setVarietyId(Integer varietyId) {
		this.varietyId = varietyId;
	}

	public Boolean getExchangeStatus() {
		return exchangeStatus;
	}

	public void setExchangeStatus(Boolean exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}

	public Integer getMarketPoint() {
		return marketPoint;
	}

	public void setMarketPoint(Integer marketPoint) {
		this.marketPoint = marketPoint;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

}
