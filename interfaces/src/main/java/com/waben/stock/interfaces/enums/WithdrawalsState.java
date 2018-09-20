package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 提现状态
 * 
 * @author luomengan
 *
 */
public enum WithdrawalsState implements CommonalityEnum {

	UNKONWN("0","未知状态"),
	
	PROCESSED("1", "处理成功"),

	PROCESSING("2", "处理中"),

	FAILURE("3", "处理失败"),
	
	RETREAT("4", "已退汇");

	WithdrawalsState(String index, String status) {
		this.index = index;
		this.status = status;
	}

	private String index;

	private String status;

	private static Map<String, WithdrawalsState> valueMap = new HashMap<String, WithdrawalsState>();

	static {
		for (WithdrawalsState _enum : WithdrawalsState.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static WithdrawalsState getByIndex(String index) {
		WithdrawalsState result = valueMap.get(index);
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
