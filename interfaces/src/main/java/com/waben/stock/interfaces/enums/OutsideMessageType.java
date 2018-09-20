package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 站外消息类型
 * 
 * @author luomengan
 *
 */
public enum OutsideMessageType implements CommonalityEnum {

	BUY_POSTED("1", "已发布"),

	BUY_BUYLOCK("2", "买入锁定"),

	BUY_HOLDPOSITION("3", "持仓中"),

	BUY_SELLAPPLY("4", "卖出申请"),
	
	BUY_SELLLOCK("5", "卖出锁定"),

	BUY_UNWIND("6", "已平仓"),

	BUY_BUYFAILED("7", "买入失败"),

	BUY_SELLFAILED("8", "卖出失败"),
	
	ACCOUNT_RECHARGESUCCESS("9", "充值成功"),
	
	ACCOUNT_WITHDRAWALSSUCCESS("10", "提现成功"),
	
	ACCOUNT_WITHDRAWALFAILED("11", "提现失败"),
	
	OPTION_WAITCONFIRMED("12", "待确认"),

	OPTION_FAILURE("13", "申购失败"),
	
	OPTION_TURNOVER("14", "持仓中"),

	OPTION_INSETTLEMENT("15", "结算中"),

	OPTION_SETTLEMENTED("16", "已结算");

	private String index;
	private String type;

	OutsideMessageType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, OutsideMessageType> valueMap = new HashMap<>();

	static {
		for (OutsideMessageType _enum : OutsideMessageType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static OutsideMessageType getByType(String index) {
		OutsideMessageType result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

	@Override
	public String getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}
}
