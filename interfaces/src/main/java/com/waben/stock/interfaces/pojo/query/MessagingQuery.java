package com.waben.stock.interfaces.pojo.query;

import java.util.Date;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
public class MessagingQuery extends PageAndSortQuery {

	private String title;

	private String messageType;

	private Date beginTime;

	private Date endTime;

	private Boolean isOutside;

	private Long publisherId;

	private Boolean hasRead;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsOutside() {
		return isOutside;
	}

	public void setIsOutside(Boolean isOutside) {
		this.isOutside = isOutside;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public Boolean getHasRead() {
		return hasRead;
	}

	public void setHasRead(Boolean hasRead) {
		this.hasRead = hasRead;
	}

}
