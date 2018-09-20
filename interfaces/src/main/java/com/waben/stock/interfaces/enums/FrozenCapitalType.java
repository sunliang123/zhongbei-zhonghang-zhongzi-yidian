package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 冻结类型
 * 
 * @author luomengan
 *
 */
public enum FrozenCapitalType implements CommonalityEnum {

	ReserveFund("1", "冻结履约保证金"),

	Withdrawals("2", "冻结提现金额");

	private String index;

	private String status;

	private FrozenCapitalType(String index, String status) {
		this.index = index;
		this.status = status;
	}

	private static Map<String, FrozenCapitalType> valueMap = new HashMap<String, FrozenCapitalType>();

	static {
		for (FrozenCapitalType _enum : FrozenCapitalType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static FrozenCapitalType getByIndex(String index) {
		FrozenCapitalType result = valueMap.get(index);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
