package com.waben.stock.datalayer.publisher.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "capital_account_record")
public class CapitalAccountRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 修改之前账户余额
	 */
	@Column(name = "update_before_balance")
	private BigDecimal updateBeforebalance;
	/**
	 * 修改之后账户余额
	 */
	@Column(name = "update_after_balance")
	private BigDecimal updateAfterBalance;
	/**
	 * 修改之前账户可用余额
	 */
	@Column(name = "update_before_available_balance")
	private BigDecimal updateBeforeAvailableBalance;

	/**
	 * 修改之后账户可用余额
	 */
	@Column(name = "update_after_available_balance")
	private BigDecimal updateAfterAvailableBalance;
	/**
	 * 修改之前冻结资金
	 */
	@Column(name = "update_before_frozen_capital")
	private BigDecimal updateBeforeFrozenCapital;

	/**
	 * 修改之后冻结资金
	 */
	@Column(name = "update_after_frozen_capital")
	private BigDecimal updateAfterFrozenCapital;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;


	/**
	 * 发布人手机号
	 */
	@Column(name = "publisher_phone")
	private String publisherPhone;

	/**
	 * 资金账户
	 */
	@JoinColumn(name = "capitalAccount")
	@ManyToOne
	private CapitalAccount capitalAccount;

	/**
	 * 修改人
	 */
	private Long staff;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getUpdateBeforebalance() {
		return updateBeforebalance;
	}

	public void setUpdateBeforebalance(BigDecimal updateBeforebalance) {
		this.updateBeforebalance = updateBeforebalance;
	}

	public BigDecimal getUpdateAfterBalance() {
		return updateAfterBalance;
	}

	public void setUpdateAfterBalance(BigDecimal updateAfterBalance) {
		this.updateAfterBalance = updateAfterBalance;
	}

	public BigDecimal getUpdateBeforeAvailableBalance() {
		return updateBeforeAvailableBalance;
	}

	public void setUpdateBeforeAvailableBalance(BigDecimal updateBeforeAvailableBalance) {
		this.updateBeforeAvailableBalance = updateBeforeAvailableBalance;
	}

	public BigDecimal getUpdateAfterAvailableBalance() {
		return updateAfterAvailableBalance;
	}

	public void setUpdateAfterAvailableBalance(BigDecimal updateAfterAvailableBalance) {
		this.updateAfterAvailableBalance = updateAfterAvailableBalance;
	}

	public BigDecimal getUpdateBeforeFrozenCapital() {
		return updateBeforeFrozenCapital;
	}

	public void setUpdateBeforeFrozenCapital(BigDecimal updateBeforeFrozenCapital) {
		this.updateBeforeFrozenCapital = updateBeforeFrozenCapital;
	}

	public BigDecimal getUpdateAfterFrozenCapital() {
		return updateAfterFrozenCapital;
	}

	public void setUpdateAfterFrozenCapital(BigDecimal updateAfterFrozenCapital) {
		this.updateAfterFrozenCapital = updateAfterFrozenCapital;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public CapitalAccount getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(CapitalAccount capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public Long getStaff() {
		return staff;
	}

	public void setStaff(Long staff) {
		this.staff = staff;
	}
}
