package com.waben.stock.datalayer.organization.entity;

import java.math.BigDecimal;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.waben.stock.datalayer.organization.entity.enumconverter.BenefitConfigTypeConverter;
import com.waben.stock.interfaces.enums.BenefitConfigType;

/**
 * 分成配置
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "p_benefit_config")
public class BenefitConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 分成配置类型
	 */
	@Convert(converter = BenefitConfigTypeConverter.class)
	private BenefitConfigType type;
	/**
	 * 分成比例
	 */
	private BigDecimal ratio;
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
	 * 对应的机构
	 */
	@OneToOne
	@JoinColumn(name = "org_id")
	private Organization org;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BenefitConfigType getType() {
		return type;
	}

	public void setType(BenefitConfigType type) {
		this.type = type;
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

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

}
