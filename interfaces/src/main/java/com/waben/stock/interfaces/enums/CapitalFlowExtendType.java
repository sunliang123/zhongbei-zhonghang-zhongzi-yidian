package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 资金流水扩展 类型
 * 
 * @author luomengan
 *
 */
public enum CapitalFlowExtendType implements CommonalityEnum {

	BUYRECORD("1", "点买记录"),

	PUBLISHER("2", "发布人"),

	STOCKOPTIONTRADE("3", "期权交易信息"),

	PAYMENTORDER("4", "支付订单"),

	WITHDRAWALSORDER("5", "提现订单");

	private String index;
	private String type;

	private CapitalFlowExtendType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, CapitalFlowExtendType> valueMap = new HashMap<String, CapitalFlowExtendType>();

	static {
		for (CapitalFlowExtendType _enum : CapitalFlowExtendType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static CapitalFlowExtendType getByIndex(String index) {
		CapitalFlowExtendType result = valueMap.get(index);
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
