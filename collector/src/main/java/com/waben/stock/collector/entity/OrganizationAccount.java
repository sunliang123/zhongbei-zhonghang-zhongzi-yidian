package com.waben.stock.collector.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 机构账户
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_organization_account")
public class OrganizationAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
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
	 * 支付密码
	 */
	private String paymentPassword;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 对应的机构
	 */
	private Long orgId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
