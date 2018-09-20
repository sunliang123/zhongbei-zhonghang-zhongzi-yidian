package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 机构账户流水 类型
 * 
 * @author luomengan
 *
 */
public enum OrganizationAccountFlowType implements CommonalityEnum {

	ServiceFeeAssign("1", "信息服务费分成"),

	DeferredChargesAssign("2", "递延费分成"),

	RightMoneyAssign("3", "期权权利金收益分成"),
	
	Withdrawals("4", "提现"),
	
	WithdrawalsFailure("5", "提现失败退回"),
	
	ProcessFee("6", "手续费");

	private String index;

	private String type;

	private OrganizationAccountFlowType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, OrganizationAccountFlowType> valueMap = new HashMap<String, OrganizationAccountFlowType>();

	static {
		for (OrganizationAccountFlowType _enum : OrganizationAccountFlowType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static OrganizationAccountFlowType getByIndex(String index) {
		OrganizationAccountFlowType result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
