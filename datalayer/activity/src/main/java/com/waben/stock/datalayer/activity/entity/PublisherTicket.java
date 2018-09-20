package com.waben.stock.datalayer.activity.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户实物实体
 * 
 * @author guowei 2018/4/11
 *
 */

@Entity
@Table(name = "publisher_ticket")
public class PublisherTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ptId;
	private long publisherId;
	
	
	/**
	 * 有效期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
	
	/**
	 * 活动参与关联Id
	 * 
	 */
	private long apId;

	/**
	 * 中奖时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date winningTime;

	public long getApId() {
		return apId;
	}

	public void setApId(long apId) {
		this.apId = apId;
	}

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

	public Date getWinningTime() {
		return winningTime;
	}

	public void setWinningTime(Date winningTime) {
		this.winningTime = winningTime;
	}
}
