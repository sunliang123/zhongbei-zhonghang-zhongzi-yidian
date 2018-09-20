package com.waben.stock.datalayer.message.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.waben.stock.datalayer.message.entity.converter.MessageTypeConverter;
import com.waben.stock.datalayer.message.entity.converter.OutsideMessageTypeConverter;
import com.waben.stock.datalayer.message.entity.converter.ResourceTypeConverter;
import com.waben.stock.interfaces.enums.MessageType;
import com.waben.stock.interfaces.enums.OutsideMessageType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * @author Created by yuyidi on 2018/1/3.
 * @desc
 */
@Entity
@Table(name = "messaging")
public class Messaging {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30, nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	@Column(length = 512)
	private String link;
	@Column(name = "type")
	@Convert(converter = MessageTypeConverter.class)
	private MessageType type;
	@Column(name = "is_outside")
	private Boolean isOutside;
	@Convert(converter = OutsideMessageTypeConverter.class)
	@Column(name = "outside_msg_type")
	private OutsideMessageType outsideMsgType;
	@OneToMany(mappedBy = "message")
	private Set<MessageReceipt> receipts;
	@Column(name = "create_time")
	private Date createTime;
	@Convert(converter = ResourceTypeConverter.class)
	@Column(name = "resource_type")
	private ResourceType resourceType;
	@Column(name = "resource_id")
	private Long resourceId;
	/**
	 * 是否已读
	 */
	@Transient
	private Boolean hasRead;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public Set<MessageReceipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(Set<MessageReceipt> receipts) {
		this.receipts = receipts;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Boolean getHasRead() {
		if (hasRead == null) {
			return false;
		}
		return hasRead;
	}

	public void setHasRead(Boolean hasRead) {
		this.hasRead = hasRead;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
