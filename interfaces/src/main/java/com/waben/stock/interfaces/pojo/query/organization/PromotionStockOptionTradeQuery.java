package com.waben.stock.interfaces.pojo.query.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

/**
 * 期权查询条件
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotionStockOptionTradeQuery extends PageAndSortQuery {

	/**
	 * 当前机构代码
	 */
	private String currentOrgCode;
	/**
	 * 交易ID
	 */
	private String tradeId;
	/**
	 * 期权状态
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
	/**
	 * 用户类型，0表示所有，1表示正式用户，2表示测试用户
	 */
	private Integer userType;

	public String getCurrentOrgCode() {
		return currentOrgCode;
	}

	public void setCurrentOrgCode(String currentOrgCode) {
		this.currentOrgCode = currentOrgCode;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
