package com.waben.stock.interfaces.dto.activity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 抽奖活动奖品比率设置
 * 
 * @author guowei 2018/4/11
 *
 */

public class DrawActivityRadioDto {
	
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
