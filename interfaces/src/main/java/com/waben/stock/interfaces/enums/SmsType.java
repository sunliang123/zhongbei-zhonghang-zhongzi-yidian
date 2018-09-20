package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

public enum SmsType implements CommonalityEnum {

	RegistVerificationCode("1", "APP注册验证码"),

	ModifyPasswordCode("2", "APP修改密码验证码"),

	BindCardCode("3", "APP绑定银行卡验证码"),

	StrategyWarning("4", "APP点买持仓提醒"),
	
	ModifyPaymentPwdCode("5", "APP修改支付密码");

	private String index;

	private String type;

	private SmsType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, SmsType> valueMap = new HashMap<String, SmsType>();

	static {
		for (SmsType _enum : SmsType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static SmsType getByIndex(String index) {
		SmsType result = valueMap.get(index);
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
