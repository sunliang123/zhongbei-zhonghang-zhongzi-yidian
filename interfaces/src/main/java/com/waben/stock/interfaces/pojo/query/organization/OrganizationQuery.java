package com.waben.stock.interfaces.pojo.query.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

/**
 * 机构查询条件
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationQuery extends PageAndSortQuery {

	/**
	 * 机构Id
	 */
	private Long orgId;
	/**
	 * 机构代码
	 */
	private String code;
	/**
	 * 机构状态
	 */
	private String state;
	/**
	 * 父级机构ID
	 */
	private Long parentId;

	private Long loginOrgId;

	private boolean onlyLoginOrg;

	/**
	 * 代理商名称
	 */
	private String organizationName;

	/**
	 * 层级
	 */
	private String level;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getLoginOrgId() {
		return loginOrgId;
	}

	public void setLoginOrgId(Long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}

	public boolean isOnlyLoginOrg() {
		return onlyLoginOrg;
	}

	public void setOnlyLoginOrg(boolean onlyLoginOrg) {
		this.onlyLoginOrg = onlyLoginOrg;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
