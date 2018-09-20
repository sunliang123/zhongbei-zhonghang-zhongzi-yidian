package com.waben.stock.datalayer.activity.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户话费券实体
 * 
 * @author guowei 2018/4/10
 *
 */
@Entity
@Table(name = "publisher_telecharge")
public class PublisherTeleCharge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ptId;
	
	
	private long publiserId;
	
	/**
	 * 有效期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date validDate;
	
	/**
	 * 话费金额
	 */
	private BigDecimal amount;
	
	/**
	 * 活动参与人关联实体Id
	 */
	private long apId;
	
	/**
	 * 抵扣券状态 1:未使用 2:已使用 3：已过期
	 */
	private int status;
	
	/**
	 * 是否充值
	 */
	private boolean ispay;
	
	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 中奖时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date winningTime;

	public long getPtId() {
		return ptId;
	}

	public void setPtId(long ptId) {
		this.ptId = ptId;
	}

	public long getPubliserId() {
		return publiserId;
	}

	public void setPubliserId(long publiserId) {
		this.publiserId = publiserId;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public long getApId() {
		return apId;
	}

	public void setApId(long apId) {
		this.apId = apId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isIspay() {
		return ispay;
	}

	public void setIspay(boolean ispay) {
		this.ispay = ispay;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getWinningTime() {
		return winningTime;
	}

	public void setWinningTime(Date winningTime) {
		this.winningTime = winningTime;
	}
}
