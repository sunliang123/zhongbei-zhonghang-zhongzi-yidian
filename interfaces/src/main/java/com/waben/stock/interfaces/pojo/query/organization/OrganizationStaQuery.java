package com.waben.stock.interfaces.pojo.query.organization;

import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

/**
 * 代理商统计查询
 * 
 * @author Administrator
 *
 */
public class OrganizationStaQuery extends PageAndSortQuery {

	/**
	 * 当前用户ID
	 */
	private Long currentOrgId;
	/**
	 * 查询类型
	 * <p>
	 * 1查询单个代理商，2查询直属代理商
	 * </p>
	 */
	private Integer queryType;
	/**
	 * 代理商代码
	 */
	private String orgCode;
	/**
	 * 代理商名称
	 */
	private String orgName;

	public Long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(Long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
