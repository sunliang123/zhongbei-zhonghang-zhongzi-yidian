package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 机构提现申请状态
 * 
 * @author luomengan
 *
 */
public enum WithdrawalsApplyState implements CommonalityEnum {

	TOBEAUDITED("1", "待审核"),

	REFUSED("2", "已拒绝"),

	PROCESSING("3", "提现中"),

	SUCCESS("4", "提现成功"),

	FAILURE("5", "提现失败");

	WithdrawalsApplyState(String index, String state) {
		this.index = index;
		this.state = state;
	}

	private String index;

	private String state;

	private static Map<String, WithdrawalsApplyState> valueMap = new HashMap<String, WithdrawalsApplyState>();

	static {
		for (WithdrawalsApplyState _enum : WithdrawalsApplyState.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static WithdrawalsApplyState getByIndex(String index) {
		WithdrawalsApplyState result = valueMap.get(index);
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
