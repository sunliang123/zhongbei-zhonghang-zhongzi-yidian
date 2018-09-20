package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentType implements CommonalityEnum {

	UnionPay("1", "银联支付"),

	ScanPay("2", "扫码支付"),

	AliAppPay("3", "支付宝-APP支付"),
	
	AliTurnPay("4", "支付宝-转账支付"),
	
	QuickPay("5", "快捷支付");

	private String index;

	private String type;

	private PaymentType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, PaymentType> map = new HashMap<String, PaymentType>();

	static {
		for (PaymentType _enum : PaymentType.values()) {
			map.put(_enum.getIndex(), _enum);
		}
	}

	public static PaymentType getByIndex(String index) {
		return map.get(index);
	}

	/*********************** setter and getter **************************/

	public String getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}

}
