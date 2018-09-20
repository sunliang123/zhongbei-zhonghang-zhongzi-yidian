package com.waben.stock.datalayer.activity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 抽奖活动
 * 
 * @author guowei 2018/4/11
 *
 */

@Entity
@Table(name = "draw_activity")
public class DrawActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public DrawActivity(){
		
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
