package com.waben.stock.datalayer.organization.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.waben.stock.datalayer.organization.entity.enumconverter.OrganizationAccountFlowTypeConverter;
import com.waben.stock.datalayer.organization.entity.enumconverter.ResourceTypeConverter;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 机构账户流水
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "p_organization_account_flow")
public class OrganizationAccountFlow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 流水号
	 */
	@Column(name = "flow_no")
	private String flowNo;
	/**
	 * 金额
	 */
	@Column(name = "amount")
	private BigDecimal amount;
	/**
	 * 原始资金
	 */
	@Column(name = "origin_amount")
	private BigDecimal originAmount;
	/**
	 * 账户可用余额
	 */
	@Column(name = "available_balance")
	private BigDecimal availableBalance;
	/**
	 * 流水类型
	 */
	@Convert(converter = OrganizationAccountFlowTypeConverter.class)
	private OrganizationAccountFlowType type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 产生时间
	 */
	private Date occurrenceTime;
	/**
	 * 对应的机构
	 */
	@OneToOne
	@JoinColumn(name = "org_id")
	private Organization org;
	/**
	 * 对应的资源类型
	 */
	@Convert(converter = ResourceTypeConverter.class)
	private ResourceType resourceType;
	/**
	 * 对应的资源ID
	 */
	private Long resourceId;
	/**
	 * 对应的资源交易单号
	 */
	private String resourceTradeNo;

	@Transient
	private OrganizationDto orgDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public OrganizationAccountFlowType getType() {
		return type;
	}

	public void setType(OrganizationAccountFlowType type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public OrganizationDto getOrgDto() {
		return orgDto;
	}

	public void setOrgDto(OrganizationDto orgDto) {
		this.orgDto = orgDto;
	}

	public BigDecimal getOriginAmount() {
		return originAmount;
	}

	public void setOriginAmount(BigDecimal originAmount) {
		this.originAmount = originAmount;
	}

	public String getResourceTradeNo() {
		return resourceTradeNo;
	}

	public void setResourceTradeNo(String resourceTradeNo) {
		this.resourceTradeNo = resourceTradeNo;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

}
