package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;

public class AdminAgentDetailDto extends OrganizationDto {

	/**
	 * 线下代理
	 */
	private Integer childOrgCount;

	/**
	 * 推广客户
	 */
	private Integer publisherCount;

	/**
	 * 账户余额
	 */
	private BigDecimal balance;

	/**
	 * 代理商姓名
	 */
	private String agnetName;

	/**
	 * 手机号
	 */
	private String phone;

	public Integer getChildOrgCount() {
		return childOrgCount;
	}

	public void setChildOrgCount(Integer childOrgCount) {
		this.childOrgCount = childOrgCount;
	}

	public Integer getPublisherCount() {
		return publisherCount;
	}

	public void setPublisherCount(Integer publisherCount) {
		this.publisherCount = publisherCount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAgnetName() {
		return agnetName;
	}

	public void setAgnetName(String agnetName) {
		this.agnetName = agnetName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
