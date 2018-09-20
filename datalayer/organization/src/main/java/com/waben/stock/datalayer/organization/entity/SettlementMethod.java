package com.waben.stock.datalayer.organization.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 结算方式
 * 
 * @author sunliang
 *
 */
@Entity
@Table(name = "settlement_method")
public class SettlementMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 结算方式
	 * <p>1：分成结算</p>
	 * <p>2：返佣结算</p>
	 */
	private Integer settlementType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

}
