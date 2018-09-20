package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付状态
 * 
 * @author luomengan
 *
 */
public enum PaymentState implements CommonalityEnum {

	Unpaid("1", "未支付"),

	Paid("2", "已支付"),

	Failure("3", "支付失败"),
	
	PartPaid("4", "部分支付");

	PaymentState(String index, String status) {
		this.index = index;
		this.status = status;
	}

	private String index;

	private String status;

	private static Map<String, PaymentState> valueMap = new HashMap<String, PaymentState>();

	static {
		for (PaymentState _enum : PaymentState.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static PaymentState getByIndex(String index) {
		PaymentState result = valueMap.get(index);
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
