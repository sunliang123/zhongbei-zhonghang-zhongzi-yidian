package com.waben.stock.interfaces.vo.message;

import java.util.Date;

import com.waben.stock.interfaces.enums.OutsideMessageType;
import com.waben.stock.interfaces.enums.ResourceType;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonValue;
import com.waben.stock.interfaces.enums.MessageType;

/**
 * 
 * @author Created by hujian on 2018年1月5日
 */
public class MessagingVo {

	private Long id;
	private Long publisherId;
	private String title;

	private String content;

	private String link;

	private Date createTime = new Date();
	private MessageType type;
	private Boolean isOutside;
	private OutsideMessageType outsideMsgType;
	private ResourceType resourceType;
	private Long resourceId;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MessageType getType() {
		return type;
	}
	
	public void setType(MessageType type) {
		this.type = type;
	}

	public Boolean getIsOutside() {
		return isOutside;
	}

	public void setIsOutside(Boolean isOutside) {
		this.isOutside = isOutside;
	}

	public OutsideMessageType getOutsideMsgType() {
		return outsideMsgType;
	}

	public void setOutsideMsgType(OutsideMessageType outsideMsgType) {
		this.outsideMsgType = outsideMsgType;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
}
