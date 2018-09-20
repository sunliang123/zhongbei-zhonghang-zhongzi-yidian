package com.waben.stock.datalayer.publisher.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/***
 * @author yuyidi 2017-11-13 23:11:02
 * @class com.waben.stock.datalayer.publisher.entity.CapitalAccount
 * @description 资金账户
 */
@Entity
@Table(name = "capital_account")
public class CapitalAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 账户余额
	 */
	@Column(name = "balance")
	private BigDecimal balance;
	/**
	 * 账户可用余额
	 */
	@Column(name = "available_balance")
	private BigDecimal availableBalance;
	/**
	 * 冻结资金
	 */
	@Column(name = "frozen_capital")
	private BigDecimal frozenCapital;
	/**
	 * 支付密码
	 */
	@Column(name = "payment_password")
	private String paymentPassword;
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2冻结，不能提现、不能交易</li>
	 * </ul>
	 */
	private Integer state;
	/**
	 * 发布人ID
	 */
	@Column(name = "publisher_id")
	@Transient
	private Long publisherId;
	/**
	 * 发布人序列号
	 */
	@Column(name = "publisher_serial_code")
	private String publisherSerialCode;

	@JoinColumn(name = "publisher_id")
	@OneToOne
	private Publisher publisher;

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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		publisher.setId(publisherId);
		this.publisher = publisher;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
