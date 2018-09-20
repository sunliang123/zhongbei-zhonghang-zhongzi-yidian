package com.waben.stock.interfaces.dto.activity;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 
 * 运营活动参与人员关联实体
 * 
 * @author guowei 2018/4/10
 *
 */

public class ActivityPublisherDto {
	
	private long apId;
	
	private long activityId;
	
	private long publisherId;
	
	/**
	 * 是否领取奖励
	 */
	private boolean isgetaward;
	
	/**
	 * 领取时间
	 */
	private Date getawardTime;
	
	/**
	 * 奖励是否有效
	 */
	private boolean isvalid;
	
	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 奖品类型 1:期权抵扣券 2：话费券  3：实物奖品
	 */
	private String awardType;
	
	/**
	 * 实物奖品
	 */
	private String material;

	public long getApId() {
		return apId;
	}

	public void setApId(long apId) {
		this.apId = apId;
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

	public boolean isIsgetaward() {
		return isgetaward;
	}

	public void setIsgetaward(boolean isgetaward) {
		this.isgetaward = isgetaward;
	}

	public Date getGetawardTime() {
		return getawardTime;
	}

	public void setGetawardTime(Date getawardTime) {
		this.getawardTime = getawardTime;
	}

	public boolean isIsvalid() {
		return isvalid;
	}

	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	
}
