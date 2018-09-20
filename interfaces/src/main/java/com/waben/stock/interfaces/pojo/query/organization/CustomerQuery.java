package com.waben.stock.interfaces.pojo.query.organization;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

/**
 * 客户查询条件
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerQuery extends PageAndSortQuery {

	/**
	 * 当前登陆用户所属的代理商ID
	 */
	private Long currentOrgId;
	/**
	 * 客户ID
	 */
	private String publisherId;
	/**
	 * 客户手机号
	 */
	private String publisherPhone;
	/**
	 * 发布人姓名
	 */
	private String publisherName;
	/**
	 * 查询的代理商代码或名称
	 */
	private String orgCodeOrName;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2黑名单，不能正常登陆</li>
	 * </ul>
	 */
	private Integer state;
	/**
	 * 是否为测试
	 */
	private Boolean isTest;
	/**
	 * 注册时间-查询开始时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 注册时间-查询结束时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public Long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(Long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getOrgCodeOrName() {
		return orgCodeOrName;
	}

	public void setOrgCodeOrName(String orgCodeOrName) {
		this.orgCodeOrName = orgCodeOrName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
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

}
