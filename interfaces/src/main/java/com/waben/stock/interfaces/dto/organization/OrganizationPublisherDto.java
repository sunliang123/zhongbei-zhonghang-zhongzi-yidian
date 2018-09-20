package com.waben.stock.interfaces.dto.organization;

import java.util.Date;

/**
 * 机构推广的发布人
 * 
 * @author luomengan
 *
 */
public class OrganizationPublisherDto {

	private Long id;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 机构代码
	 */
	private String orgCode;
	/**
	 * 机构ID
	 */
	private Long orgId;
	/**
	 * 创建时间
	 */
	private Date createTime;

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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
