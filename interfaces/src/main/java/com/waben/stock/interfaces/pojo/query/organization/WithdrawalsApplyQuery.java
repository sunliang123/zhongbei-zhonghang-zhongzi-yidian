package com.waben.stock.interfaces.pojo.query.organization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalsApplyQuery extends PageAndSortQuery {

	/**
	 * 申请ID
	 */
	private Long applyId;
	/**
	 * 状态
	 */
	private String states;
	/**
	 * 当前登陆用户所属的代理商ID
	 */
	private Long currentOrgId;
	/**
	 * 查询的代理商代码或名称
	 */
	private String orgCodeOrName;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public Long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(Long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public String getOrgCodeOrName() {
		return orgCodeOrName;
	}

	public void setOrgCodeOrName(String orgCodeOrName) {
		this.orgCodeOrName = orgCodeOrName;
	}

}
