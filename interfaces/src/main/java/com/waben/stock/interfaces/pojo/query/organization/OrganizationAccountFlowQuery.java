package com.waben.stock.interfaces.pojo.query.organization;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

public class OrganizationAccountFlowQuery extends PageAndSortQuery {

	/**
	 * 流水类型
	 */
	private String types;
	/**
	 * 当前登陆用户所属的代理商ID
	 */
	private Long currentOrgId;
	/**
	 * 交易编号
	 */
	private String tradeNo;
	/**
	 * 流水号
	 */
	private String flowNo;
	/**
	 * 客户手机号
	 */
	private String publisherPhone;
	/**
	 * 发布人姓名
	 */
	private String publisherName;
	/**
	 * 行权周期
	 */
	private Long cycleId;
	/**
	 * 流水时间-查询开始时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 流水时间-查询结束时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 股票代码或者名称
	 */
	private String stockCodeOrName;
	/**
	 * 查询代理商代码或者名称
	 */
	private String orgCodeOrName;
	/**
	 * 查询类型
	 * <ul>
	 * <li>1查询当前代理商的流水</li>
	 * <li>2查询分成（如果是一级代理商则查询所有代理商的分成，如果是其他级代理商则查询自己+直属代理商的分成）</li>
	 * </ul>
	 */
	private Integer queryType;

	public Long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(Long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
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

	public String getStockCodeOrName() {
		return stockCodeOrName;
	}

	public void setStockCodeOrName(String stockCodeOrName) {
		this.stockCodeOrName = stockCodeOrName;
	}

	public String getOrgCodeOrName() {
		return orgCodeOrName;
	}

	public void setOrgCodeOrName(String orgCodeOrName) {
		this.orgCodeOrName = orgCodeOrName;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
