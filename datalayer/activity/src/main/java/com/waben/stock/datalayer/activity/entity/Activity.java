package com.waben.stock.datalayer.activity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 运营活动
 * 
 * @author guowei 2018/4/10
 *
 */
@Entity
@Table(name = "operation_activity")
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long activityId;
	
	/**
	 * 活动主标题
	 */
	private String subject;
	
	/**
	 * 活动副标题
	 */
	private String subject2;
	
	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	/**
	 * 是否生效
	 */
	private boolean isvalid;
	
	/**
	 * 活动详情
	 */
	
	@Column(length=2000)
	private String content;
	
	/**
	 * 活动链接
	 */
	private String href;
	
	/**
	 * 活动页pv
	 */
	private int pv;
	
	/**
	 * 活动页uv
	 */
	private int uv;
	
	/**
	 * 活动类型 0优股网 1合作方
	 */
	private int activityType;
	
	/**
	 * 活动资格  0 所有用户
	 */
	private int qualification;
	
	/**
	 * 活动参与用户数
	 */
	private int usercount;
	
	/**
	 * 活动是否结束
	 */
	private boolean isend;
	
	/**
	 * 奖品类型 1:期权抵扣券  2：话费券  3：实物奖品
	 */
	private String awardType;

	/**
	 * 活动展示位置
	 */
	private String location;

	public Activity(){
		
	}
	
	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject2() {
		return subject2;
	}

	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isIsvalid() {
		return isvalid;
	}

	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	public int getQualification() {
		return qualification;
	}

	public void setQualification(int qualification) {
		this.qualification = qualification;
	}

	public int getUsercount() {
		return usercount;
	}

	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

	public boolean isIsend() {
		return isend;
	}

	public void setIsend(boolean isend) {
		this.isend = isend;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
