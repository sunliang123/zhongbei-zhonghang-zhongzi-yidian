package com.waben.stock.interfaces.pojo.form.organization;

import java.math.BigDecimal;

public class PriceMarkupForm {

	/**
	 * 对应的资源类型
	 * <ul>
	 * <li>1策略加价</li>
	 * <li>2期权加价</li>
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
	 * 加价比例
	 */
	private BigDecimal ratio;
	/**
	 * 对应的机构ID
	 */
	private Long orgId;

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

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
