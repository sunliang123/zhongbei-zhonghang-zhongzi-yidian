package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;

/**
 * 
 * 加价配置 Dto
 * 
 * @author luomengan
 *
 */
public class PriceMarkupConfigDto {

	private Long id;
	/**
	 * 加价比例（在上级机构的基础上的加价）
	 */
	private BigDecimal ratio;
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
	 * 对应的资源名称
	 */
	private String resourceName;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

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

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
