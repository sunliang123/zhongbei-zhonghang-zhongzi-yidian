package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 风控类型
 * 
 * @author luomengan
 *
 */
public enum WindControlType implements CommonalityEnum {
	
	TRADINGEND("1", "交易期满"),

	REACHPROFITPOINT("2", "达到止盈点"),
	
	REACHLOSSPOINT("3", "达到止损点"),
	
	PUBLISHERAPPLY("4", "客户申请平仓"),

	LIMITUP("5","涨停"),

	LIMITDOWN("6","跌停");

	private WindControlType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private String index;

	private String type;

	private static Map<String, WindControlType> valueMap = new HashMap<String, WindControlType>();

	static {
		for (WindControlType _enum : WindControlType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static WindControlType getByIndex(String index) {
		WindControlType result = valueMap.get(index);
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
