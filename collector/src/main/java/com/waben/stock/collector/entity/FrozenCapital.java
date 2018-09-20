package com.waben.stock.collector.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 冻结资金
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_frozen_capital")
public class FrozenCapital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
	/**
	 * 冻结金额
	 */
	@Column(name = "amount")
	private BigDecimal amount;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 冻结时间
	 */
	@Column(name = "frozen_time")
	private Date frozenTime;
	/**
	 * 解冻时间
	 */
	@Column(name = "thaw_time")
	private Date thawTime;
	/**
	 * 点买记录ID
	 */
	@Column(name = "buy_record_id")
	private Long buyRecordId;
	/**
	 * 提现单号
	 */
	@Column(name = "withdrawals_no")
	private String withdrawalsNo;
	/**
	 * 发布人ID
	 */
	@Column(name = "publisher_id")
	private Long publisherId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(Date frozenTime) {
		this.frozenTime = frozenTime;
	}

	public Date getThawTime() {
		return thawTime;
	}

	public void setThawTime(Date thawTime) {
		this.thawTime = thawTime;
	}

	public Long getBuyRecordId() {
		return buyRecordId;
	}

	public void setBuyRecordId(Long buyRecordId) {
		this.buyRecordId = buyRecordId;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getWithdrawalsNo() {
		return withdrawalsNo;
	}

	public void setWithdrawalsNo(String withdrawalsNo) {
		this.withdrawalsNo = withdrawalsNo;
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
