package com.waben.stock.interfaces.pojo.form.organization;

import java.math.BigDecimal;

public class BenefitConfigForm {

	/**
	 * 对应的资源ID
	 * <ul>
	 * <li>如果是策略分成，对应策略类型ID</li>
	 * <li>如果期权分成，对应期权周期ID</li>
	 * </ul>
	 */
	private Long resourceId;
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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
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

}
