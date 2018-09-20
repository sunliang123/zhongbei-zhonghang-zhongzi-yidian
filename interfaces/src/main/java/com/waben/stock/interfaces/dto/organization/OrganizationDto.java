package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.OrganizationState;

/**
 * 机构
 * 
 * @author luomengan
 *
 */
public class OrganizationDto {

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
	 * 机构对应的账户信息
	 */
	private OrganizationAccountDto accountDto;

	/**
	 * 提现手续费
	 */
	private BigDecimal billCharge;

	private String treeCode;

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

	public OrganizationAccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(OrganizationAccountDto accountDto) {
		this.accountDto = accountDto;
	}

	public BigDecimal getBillCharge() {
		return billCharge;
	}

	public void setBillCharge(BigDecimal billCharge) {
		this.billCharge = billCharge;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

}
