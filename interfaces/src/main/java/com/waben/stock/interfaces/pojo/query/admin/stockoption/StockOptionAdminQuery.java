package com.waben.stock.interfaces.pojo.query.admin.stockoption;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

public class StockOptionAdminQuery extends PageAndSortQuery {

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
	 * 名义本金
	 */
	private BigDecimal nominalAmount;
	/**
	 * 周期名称
	 */
	private String cycleId;
	/**
	 * 交易状态，多个交易状态使用,号分割
	 */
	private String state;
	/**
	 * 是否为测试单
	 */
	private Boolean isTest;
	/**
	 * 是否标记
	 */
	private Boolean isMark;
	/**
	 * 申购时间-查询开始时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 申购时间-查询结束时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 平台权利金比例-查询开始值
	 */
	private BigDecimal startRatio;
	/**
	 * 平台权利金比例-查询结束值
	 */
	private BigDecimal endRatio;
	
	/**
	 * 查询类型
	 * <ul>
	 * <li>0订单列表</li>
	 * <li>1询价列表</li>
	 * <li>2持仓列表</li>
	 * <li>3结算列表</li>
	 * <li>4撤单列表</li>
	 * </ul>
	 */
	private Integer queryType;

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

	public BigDecimal getNominalAmount() {
		return nominalAmount;
	}

	public void setNominalAmount(BigDecimal nominalAmount) {
		this.nominalAmount = nominalAmount;
	}

	public String getCycleId() {
		return cycleId;
	}

	public void setCycleId(String cycleId) {
		this.cycleId = cycleId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsMark() {
		return isMark;
	}

	public void setIsMark(Boolean isMark) {
		this.isMark = isMark;
	}

	public BigDecimal getStartRatio() {
		return startRatio;
	}

	public void setStartRatio(BigDecimal startRatio) {
		this.startRatio = startRatio;
	}

	public BigDecimal getEndRatio() {
		return endRatio;
	}

	public void setEndRatio(BigDecimal endRatio) {
		this.endRatio = endRatio;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

}