package com.waben.stock.interfaces.request.organization;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 机构账户
 * 
 * @author zzw
 *
 */
@ApiModel(description = "请求参数代理商资产对象")
public class OrganizationAccountRequest implements Serializable{
	@ApiModelProperty(value = "代理商资产id")
	private Long id;

	/**
	 * 冻结资金
	 */
	@ApiModelProperty(value = "冻结资金")
	private BigDecimal frozenCapital;

//	/**
//	 * 状态
//	 * <ul>
//	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
//	 * <li>2冻结，不能提现、不能交易</li>
//	 * </ul>
//	 */
//	private Integer state;

	@ApiModelProperty(value = "冻结原因")
	private String reason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getFrozenCapital() {
		return frozenCapital;
	}

	public void setFrozenCapital(BigDecimal frozenCapital) {
		this.frozenCapital = frozenCapital;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
