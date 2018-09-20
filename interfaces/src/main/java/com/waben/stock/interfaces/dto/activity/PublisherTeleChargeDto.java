package com.waben.stock.interfaces.dto.activity;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户话费券实体
 * 
 * @author guowei 2018/4/10
 *
 */

public class PublisherTeleChargeDto {
	
	private long ptId;
	
	
	private long publiserId;

	private String publisherPhone;

	/**
	 * 有效期
	 */
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

	private String activityName;

	/**
	 * 中奖时间
	 */
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

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getWinningTime() {
		return winningTime;
	}

	public void setWinningTime(Date winningTime) {
		this.winningTime = winningTime;
	}
}
