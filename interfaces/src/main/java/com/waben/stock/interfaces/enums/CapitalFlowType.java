package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 资金流水 类型
 * 
 * @author luomengan
 *
 */
public enum CapitalFlowType implements CommonalityEnum {

	Recharge("1", "充值"),

	Withdrawals("2", "提现"),

	ServiceFee("3", "服务费"),

	ReserveFund("4", "冻结履约保证金"),

	DeferredCharges("5", "递延费"),

	ReturnReserveFund("6", "退回履约保证金"),

	Loss("7", "亏损"),

	Profit("8", "盈利"),
	
	Promotion("9", "推广佣金"),
	
	Revoke("10", "撤单退款"),
	
	ReturnDeferredCharges("11", "退回递延费"),
	
	RightMoney("12", "策略风控服务费"),
	
	ReturnRightMoney("13", "退回策略风控服务费"),
	
	StockOptionProfit("14", "策略盈利");

	private String index;

	private String type;

	private CapitalFlowType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, CapitalFlowType> valueMap = new HashMap<String, CapitalFlowType>();

	static {
		for (CapitalFlowType _enum : CapitalFlowType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static CapitalFlowType getByIndex(String index) {
		CapitalFlowType result = valueMap.get(index);
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
