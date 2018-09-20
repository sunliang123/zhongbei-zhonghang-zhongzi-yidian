package com.waben.stock.interfaces.dto.publisher;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.FrozenCapitalStatus;
import com.waben.stock.interfaces.enums.FrozenCapitalType;

public class FrozenCapitalDto {

	private Long id;
	/**
	 * 冻结金额
	 */
	private BigDecimal amount;
	/**
	 * 状态
	 */
	private FrozenCapitalStatus status;
	/**
	 * 类型
	 */
	private FrozenCapitalType type;
	/**
	 * 冻结时间
	 */
	private Date frozenTime;
	/**
	 * 解冻时间
	 */
	private Date thawTime;
	/**
	 * 点买记录ID
	 */
	private Long buyRecordId;
	/**
	 * 提现单号
	 */
	private String withdrawalsNo;
	/**
	 * 发布人ID
	 */
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
