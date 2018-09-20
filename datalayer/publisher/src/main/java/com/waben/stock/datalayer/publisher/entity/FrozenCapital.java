package com.waben.stock.datalayer.publisher.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.waben.stock.datalayer.publisher.entity.enumconverter.FrozenCapitalStatusConverter;
import com.waben.stock.datalayer.publisher.entity.enumconverter.FrozenCapitalTypeConverter;
import com.waben.stock.interfaces.enums.FrozenCapitalStatus;
import com.waben.stock.interfaces.enums.FrozenCapitalType;

/**
 * 冻结资金
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "frozen_capital")
public class FrozenCapital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 冻结金额
	 */
	@Column(name = "amount")
	private BigDecimal amount;
	/**
	 * 状态
	 */
	@Column(name = "status")
	@Convert(converter = FrozenCapitalStatusConverter.class)
	private FrozenCapitalStatus status;
	/**
	 * 类型
	 */
	@Column(name = "type")
	@Convert(converter = FrozenCapitalTypeConverter.class)
	private FrozenCapitalType type;
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

	public FrozenCapitalStatus getStatus() {
		return status;
	}

	public void setStatus(FrozenCapitalStatus status) {
		this.status = status;
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

	public FrozenCapitalType getType() {
		return type;
	}

	public void setType(FrozenCapitalType type) {
		this.type = type;
	}

	public String getWithdrawalsNo() {
		return withdrawalsNo;
	}

	public void setWithdrawalsNo(String withdrawalsNo) {
		this.withdrawalsNo = withdrawalsNo;
	}

}
