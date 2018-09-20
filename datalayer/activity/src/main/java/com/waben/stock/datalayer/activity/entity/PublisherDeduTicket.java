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
 * 用户抵扣券实体
 * 
 * @author guowei 2018/4/10
 *
 */
@Entity
@Table(name = "publisher_deduTicket")
public class PublisherDeduTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dtId;
	
	
	private long publiserId;
	
	/**
	 * 使用方式 1:限期权申购使用
	 */
	private int useType;
	
	/**
	 * 限期权申购使用;
	 */
	private String useTypeName;
	
	/**
	 * 券名称
	 */
	private String ticketName;
	
	
	/**
	 * 有效期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date validDate;
	
	/**
	 * 抵扣金额
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
	 * 备注
	 */
	private String memo;
	/**
	 * 中奖时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date winningTime;
	
	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getUseTypeName() {
		return useTypeName;
	}

	public void setUseTypeName(String useTypeName) {
		this.useTypeName = useTypeName;
	}

	public long getDtId() {
		return dtId;
	}

	public void setDtId(long dtId) {
		this.dtId = dtId;
	}

	public long getPubliserId() {
		return publiserId;
	}

	public void setPubliserId(long publiserId) {
		this.publiserId = publiserId;
	}

	public int getUseType() {
		return useType;
	}

	public void setUseType(int useType) {
		this.useType = useType;
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
