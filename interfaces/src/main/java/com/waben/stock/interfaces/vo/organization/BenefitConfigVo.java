package com.waben.stock.interfaces.vo.organization;

import java.math.BigDecimal;

public class BenefitConfigVo {

	/**
	 * 对应的资源类型
	 * <ul>
	 * <li>1策略分成</li>
	 * <li>2期权分成</li>
	 * </ul>
	 */
	private Integer resourceType;
	/**
	 * 对应的资源ID
	 * <ul>
	 * <li>如果是策略分成，对应策略类型ID</li>
	 * <li>如果期权分成，对应期权周期ID</li>
	 * </ul>
	 */
	private Long resourceId;
	/**
	 * 对应的资源名称
	 */
	private String resourceName;
	/**
	 * 服务费分成比例
	 */
	private BigDecimal serviceFeeRatio;
	/**
	 * 递延费分成比例
	 */
	private BigDecimal deferredFeeRatio;
	/**
	 * 权利金分成比例
	 */
	private BigDecimal rightMoneyRatio;
	/**
	 * 对应的机构ID
	 */
	private Long orgId;
	/**
	 * 对应的机构代码
	 */
	private String orgCode;
	/**
	 * 对应的机构名称
	 */
	private String orgName;

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public BigDecimal getServiceFeeRatio() {
		return serviceFeeRatio;
	}

	public void setServiceFeeRatio(BigDecimal serviceFeeRatio) {
		this.serviceFeeRatio = serviceFeeRatio;
	}

	public BigDecimal getDeferredFeeRatio() {
		return deferredFeeRatio;
	}

	public void setDeferredFeeRatio(BigDecimal deferredFeeRatio) {
		this.deferredFeeRatio = deferredFeeRatio;
	}

	public BigDecimal getRightMoneyRatio() {
		return rightMoneyRatio;
	}

	public void setRightMoneyRatio(BigDecimal rightMoneyRatio) {
		this.rightMoneyRatio = rightMoneyRatio;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
