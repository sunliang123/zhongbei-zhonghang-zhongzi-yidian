package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据 类型
 * 
 * @author luomengan
 *
 */
public enum AnalogDataType implements CommonalityEnum {

	STRATEGYTRADEDYNAMIC("1", "配资交易动态"),

	STOCKOPTIONTRADEDYNAMIC("2", "期权交易动态");

	private String index;

	private String type;

	private AnalogDataType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, AnalogDataType> valueMap = new HashMap<String, AnalogDataType>();

	static {
		for (AnalogDataType _enum : AnalogDataType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static AnalogDataType getByIndex(String index) {
		AnalogDataType result = valueMap.get(index);
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
