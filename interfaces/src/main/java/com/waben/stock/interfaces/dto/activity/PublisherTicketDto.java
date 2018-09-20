package com.waben.stock.interfaces.dto.activity;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实物实体
 * 
 * @author guowei 2018/4/11
 *
 */


public class PublisherTicketDto {
	
	private long ptId;
	private long publisherId;
	
	private String publisherPhone;
	/**
	 * 有效期
	 */
	private Date validDate;
	
	private String ticketName;
	
	/**
	 * 状态 1:未使用 2:已使用 3：已过期
	 */
	private int status;
	
	/**
	 * 备注
	 */
	private String memo;
	private long apId;
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

	public long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(long publisherId) {
		this.publisherId = publisherId;
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

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public long getApId() {
		return apId;
	}

	public void setApId(long apId) {
		this.apId = apId;
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
