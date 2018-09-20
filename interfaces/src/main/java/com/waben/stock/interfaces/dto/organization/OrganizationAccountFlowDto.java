package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 机构账户流水
 * 
 * @author luomengan
 *
 */
public class OrganizationAccountFlowDto {

	private Long id;
	/**
	 * 流水号
	 */
	private String flowNo;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 原始资金
	 */
	private BigDecimal originAmount;
	/**
	 * 账户可用余额
	 */
	private BigDecimal availableBalance;
	/**
	 * 流水类型
	 */
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
	private OrganizationDto orgDto;
	/**
	 * 分成配置类型
	 */
	private ResourceType resourceType;
	/**
	 * 对应的资源ID
	 */
	private Long resourceId;
	/**
	 * 对应的资源交易单号
	 */
	private String resourceTradeNo;

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

	public OrganizationDto getOrgDto() {
		return orgDto;
	}

	public void setOrgDto(OrganizationDto orgDto) {
		this.orgDto = orgDto;
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
