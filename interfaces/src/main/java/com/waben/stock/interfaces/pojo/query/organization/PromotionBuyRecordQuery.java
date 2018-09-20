package com.waben.stock.interfaces.pojo.query.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

/**
 * 策略查询条件
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionBuyRecordQuery extends PageAndSortQuery {

	/**
	 * 当前机构代码
	 */
	private String currentOrgCode;
	/**
	 * 点买记录ID
	 */
	private String buyRecordId;
	/**
	 * 策略状态
	 */
	private String state;
	/**
	 * 手机号码
	 */
	private String publisherPhone;
	/**
	 * 所属机构代码
	 */
	private String orgCode;

	public String getBuyRecordId() {
		return buyRecordId;
	}

	public void setBuyRecordId(String buyRecordId) {
		this.buyRecordId = buyRecordId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCurrentOrgCode() {
		return currentOrgCode;
	}

	public void setCurrentOrgCode(String currentOrgCode) {
		this.currentOrgCode = currentOrgCode;
	}

}
