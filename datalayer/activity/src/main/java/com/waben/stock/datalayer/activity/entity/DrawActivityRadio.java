package com.waben.stock.datalayer.activity.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 抽奖活动奖品比率设置
 * 
 * @author guowei 2018/4/11
 *
 */
@Entity
@Table(name = "draw_activity_radio")
public class DrawActivityRadio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long darId;
	
	private long activityId;
	
	private long ticketId;
	
	private BigDecimal radio;

	public long getDarId() {
		return darId;
	}

	public void setDarId(long darId) {
		this.darId = darId;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public BigDecimal getRadio() {
		return radio;
	}

	public void setRadio(BigDecimal radio) {
		this.radio = radio;
	}
	
	
}
