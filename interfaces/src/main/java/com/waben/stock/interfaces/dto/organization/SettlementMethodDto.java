package com.waben.stock.interfaces.dto.organization;
/**
 * 结算方式
 * @author Administrator
 *
 */
public class SettlementMethodDto {

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
