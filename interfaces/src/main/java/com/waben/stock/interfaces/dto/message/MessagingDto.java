package com.waben.stock.interfaces.dto.message;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.enums.MessageType;
import com.waben.stock.interfaces.enums.OutsideMessageType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagingDto {

	/**
	 * 消息id
	 */
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 跳转链接
	 */
	private String link;
	/**
	 * 消息类型
	 */
	private MessageType type;
	/**
	 * 是否站外消息
	 */
	private Boolean isOutside;
	/**
	 * 站外消息类型
	 * <ul>
	 * <li>BUY_POSTED点买通知_已发布</li>
	 * <li>BUY_BUYLOCK点买通知_买入锁定</li>
	 * <li>BUY_HOLDPOSITION点买通知_持仓中</li>
	 * <li>BUY_SELLAPPLY点买通知_卖出申请</li>
	 * <li>BUY_SELLLOCK点买通知_卖出锁定</li>
	 * <li>BUY_UNWIND点买通知_已平仓</li>
	 * <li>BUY_BUYFAILED点买通知_买入失败</li>
	 * <li>BUY_SELLFAILED点买通知_卖出失败</li>
	 * <li>ACCOUNT_RECHARGESUCCESS资金通知_充值成功</li>
	 * <li>ACCOUNT_WITHDRAWALSSUCCESS资金通知_提现成功</li>
	 * <li>ACCOUNT_WITHDRAWALFAILED资金通知_提现失败</li>
	 * </ul>
	 */
	private OutsideMessageType outsideMsgType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 资源类型
	 * <ul>
	 * <li>BUYRECORD点买记录</li>
	 * <li>PUBLISHER发布人</li>
	 * </ul>
	 */
	private ResourceType resourceType;
	/**
	 * 资源id
	 */
	private Long resourceId;
	/**
	 * 是否已读
	 */
	private Boolean hasRead;
	private Long publisherId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Boolean getOutside() {
		return isOutside;
	}

	public void setOutside(Boolean outside) {
		isOutside = outside;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}
}
