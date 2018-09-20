package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 点买状态
 * 
 * @author luomengan
 *
 */
public enum BuyRecordState implements CommonalityEnum {

	UNKONWN("0","未知状态"),

	POSTED("1", "买入中"),

	BUYLOCK("2", "买入锁定"),

	HOLDPOSITION("3", "持仓中"),
	
	SELLAPPLY("4", "卖出申请"),

	SELLLOCK("5", "卖出锁定"),

	UNWIND("6", "已平仓"),

	WITHDRAWLOCK("7","撤单锁定"),

	REVOKE("8", "撤单"),

	HASENTRUST("9","已委托");

	BuyRecordState(String index, String status) {
		this.index = index;
		this.status = status;
	}

	private String index;

	private String status;

	private static Map<String, BuyRecordState> valueMap = new HashMap<String, BuyRecordState>();

	static {
		for (BuyRecordState _enum : BuyRecordState.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static BuyRecordState getByIndex(String index) {
		BuyRecordState result = valueMap.get(index);
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
