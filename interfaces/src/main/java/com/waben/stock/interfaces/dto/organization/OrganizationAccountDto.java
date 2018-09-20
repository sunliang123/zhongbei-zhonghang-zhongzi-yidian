package com.waben.stock.interfaces.dto.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构账户
 * 
 * @author luomengan
 *
 */
public class OrganizationAccountDto implements Serializable{
	private Long id;
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
	 * 账户对应的机构
	 */
	private OrganizationDto org;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2冻结，不能提现、不能交易</li>
	 * </ul>
	 */
	private Integer state;

	/**
	 * 冻结原因
	 */
	private String reason;

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

	public OrganizationDto getOrg() {
		return org;
	}

	public void setOrg(OrganizationDto org) {
		this.org = org;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
