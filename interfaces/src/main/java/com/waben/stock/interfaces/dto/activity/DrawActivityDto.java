package com.waben.stock.interfaces.dto.activity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * 
 * 抽奖活动
 * 
 * @author guowei 2018/4/11
 *
 */
public class DrawActivityDto {

	private long drawId;

	private long activityId;

	private long publisherId;

	/**
	 * 剩余次数
	 */
	private int remaintime;
	/**
	 * 已抽奖次数
	 */
	private int luckyDrawCount;
	public DrawActivityDto(){
		
	}
	
	public long getDrawId() {
		return drawId;
	}

	public void setDrawId(long drawId) {
		this.drawId = drawId;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(long publisherId) {
		this.publisherId = publisherId;
	}

	public int getRemaintime() {
		return remaintime;
	}

	public void setRemaintime(int remaintime) {
		this.remaintime = remaintime;
	}

	public int getLuckyDrawCount() {
		return luckyDrawCount;
	}

	public void setLuckyDrawCount(int luckyDrawCount) {
		this.luckyDrawCount = luckyDrawCount;
	}
}
