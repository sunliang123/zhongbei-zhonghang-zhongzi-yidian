package com.waben.stock.applayer.strategist.payapi.wabenpay.config;

import java.util.HashMap;
import java.util.Map;

import com.waben.stock.interfaces.enums.BankType;
import com.waben.stock.interfaces.enums.CommonalityEnum;

public enum WaBenBankType implements CommonalityEnum {

	JSYH("1", "CCB", "建设银行", BankType.JSYH),

	GSYH("2", "ICBC", "工商银行", BankType.GSYH),

	NYYH("3", "ABC", "农业银行", BankType.NYYH),

	ZGYH("4", "BOC", "中国银行", BankType.ZGYH),

	JTYH("5", "BOCO", "交通银行", BankType.JTYH),

	ZXYH("6", "ECITIC", "中信银行", BankType.ZXYH),

	GDYH("7", "CEB", "光大银行", BankType.GDYH),

	HXYH("8", "HXB", "华夏银行", BankType.HXYH),

	MSYH("9", "CMBC", "民生银行", BankType.MSYH),

	GFYH("10", "GDB", "广发银行", BankType.GFYH),

	ZSYH("11", "CMB", "招商银行", BankType.ZSYH),

	XYYH("12", "CIB", "兴业银行", BankType.XYYH),

	PFYH("13", "SPDB", "浦发银行", BankType.PFYH),

	YZCX("14", "PSBC", "邮政储蓄", BankType.YZCX),

	PAYH("15", "PINGAN", "平安银行", BankType.PAYH);

	private String index;

	private String code;

	private String bank;

	private BankType plateformBankType;

	private WaBenBankType(String index, String code, String bank, BankType plateformBankType) {
		this.index = index;
		this.code = code;
		this.bank = bank;
		this.plateformBankType = plateformBankType;
	}

	private static Map<BankType, WaBenBankType> map = new HashMap<BankType, WaBenBankType>();

	static {
		for (WaBenBankType _enum : WaBenBankType.values()) {
			map.put(_enum.getPlateformBankType(), _enum);
		}
	}

	public static WaBenBankType getByPlateformBankType(BankType plateformBankType) {
		return map.get(plateformBankType);
	}

	/*********************** setter and getter **************************/

	public String getIndex() {
		return index;
	}

	public String getBank() {
		return bank;
	}

	public String getCode() {
		return code;
	}

	public BankType getPlateformBankType() {
		return plateformBankType;
	}

}
