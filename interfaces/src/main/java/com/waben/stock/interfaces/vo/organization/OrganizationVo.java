package com.waben.stock.interfaces.vo.organization;

import com.waben.stock.interfaces.dto.organization.OrganizationAccountDto;
import com.waben.stock.interfaces.enums.OrganizationState;

import java.math.BigDecimal;
import java.util.Date;

public class OrganizationVo {

	private Long id;
	/**
	 * 机构代码
	 */
	private String code;
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 机构状态
	 */
	private OrganizationState state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 父级机构ID
	 */
	private Long parentId;
	/**
	 * 父级机构代码
	 */
	private String parentCode;
	/**
	 * 父级机构代码
	 */
	private String parentName;

	/**
	 * 机构对应累计分成收入
	 * */
	private BigDecimal amount;
	/**
	 * 机构对应账户信息
	 * */
	private OrganizationAccountDto orgDto;
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public OrganizationState getState() {
		return state;
	}

	public void setState(OrganizationState state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public OrganizationAccountDto getOrgDto() {
		return orgDto;
	}

	public void setOrgDto(OrganizationAccountDto orgDto) {
		this.orgDto = orgDto;
	}
}
