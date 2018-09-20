package com.waben.stock.interfaces.pojo.query.organization;

import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "OrganizationAccountVo", description = "代理商资产查询对象")
public class OrganizationAccountQuery extends PageAndSortQuery {
	// 机构代码
	@ApiModelProperty(value = "代理商代码")
	private String orgCode;
	// 机构名称
	@ApiModelProperty(value = "代理商名称")
	private String orgName;
	// 机构ID
	@ApiModelProperty(value = "代理商id")
	private Long orgId;

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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
