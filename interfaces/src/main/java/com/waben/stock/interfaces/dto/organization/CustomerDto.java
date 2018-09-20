package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerDto {

	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 发布人手机号码
	 */
	private String publisherPhone;
	/**
	 * 发布人姓名
	 */
	private String publisherName;
	/**
	 * 从属代理商ID
	 */
	private Long orgId;
	/**
	 * 从属代理商代码
	 */
	private String orgCode;
	/**
	 * 从属代理商名称
	 */
	private String orgName;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	/**
	 * 账户可用余额
	 */
	private BigDecimal availableBalance;
	/**
	 * 冻结资金
	 */
	private BigDecimal frozenCapital;
	/**
	 * 用户注册终端
	 */
	private String endType;
	/**
	 * 是否测试用户
	 */
	private Boolean isTest;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2黑名单，不能正常登陆</li>
	 * </ul>
	 */
	private Integer state;

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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BigDecimal getFrozenCapital() {
		return frozenCapital;
	}

	public void setFrozenCapital(BigDecimal frozenCapital) {
		this.frozenCapital = frozenCapital;
	}

	public String getEndType() {
		return endType;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
