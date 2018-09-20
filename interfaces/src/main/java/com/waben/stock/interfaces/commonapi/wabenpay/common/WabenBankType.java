package com.waben.stock.interfaces.commonapi.wabenpay.common;

import java.util.HashMap;
import java.util.Map;

import com.waben.stock.interfaces.enums.BankType;
import com.waben.stock.interfaces.enums.CommonalityEnum;

public enum WabenBankType implements CommonalityEnum {

	JSYH("1", "01050000", "中国建设银行", BankType.JSYH),

	GSYH("2", "01020000", "中国工商银行", BankType.GSYH),

	NYYH("3", "01030000", "中国农业银行", BankType.NYYH),

	ZGYH("4", "01040000", "中国银行", BankType.ZGYH),

	JTYH("5", "03010000", "交通银行", BankType.JTYH),

	ZXYH("6", "03020000", "中信银行", BankType.ZXYH),

	GDYH("7", "03030000", "光大银行", BankType.GDYH),

	HXYH("8", "03040000", "华夏银行", BankType.HXYH),

	MSYH("9", "03050000", "民生银行", BankType.MSYH),

	GFYH("10", "03060000", "广东发展银行", BankType.GFYH),

	ZSYH("11", "03080000", "招商银行", BankType.ZSYH),

	XYYH("12", "03090000", "兴业银行", BankType.XYYH),

	PFYH("13", "03100000", "上海浦发银行", BankType.PFYH),

	YZCX("14", "01000000", "中国邮政储蓄银行", BankType.YZCX),

	PAYH("15", "03070000", "平安银行", BankType.PAYH),
	
	// 部分银行card_bin有别名的银行
	GSYH1("16", "01020000", "中国工商银行", BankType.GSYH1),
	
	JSYH1("17", "01050000", "中国建设银行", BankType.JSYH1),
	
	YZCX1("18", "01000000", "中国邮政储蓄银行", BankType.YZCX1),
	
	GFYH1("19", "03060000", "广东发展银行", BankType.GFYH1),
	
	PFYH1("20", "03100000", "上海浦发银行", BankType.PFYH1),
	
	PAYH1("21", "03070000", "平安银行", BankType.PAYH1),
	
	PAYH2("22", "03070000", "平安银行", BankType.PAYH2);
	
	private String index;

	private String code;

	private String bank;

	private BankType plateformBankType;

	private WabenBankType(String index, String code, String bank, BankType plateformBankType) {
		this.index = index;
		this.code = code;
		this.bank = bank;
		this.plateformBankType = plateformBankType;
	}

	private static Map<BankType, WabenBankType> map = new HashMap<BankType, WabenBankType>();

	static {
		for (WabenBankType _enum : WabenBankType.values()) {
			map.put(_enum.getPlateformBankType(), _enum);
		}
	}

	public static WabenBankType getByPlateformBankType(BankType plateformBankType) {
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
