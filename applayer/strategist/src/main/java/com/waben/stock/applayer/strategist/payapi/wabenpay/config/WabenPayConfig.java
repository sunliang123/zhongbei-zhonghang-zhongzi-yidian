package com.waben.stock.applayer.strategist.payapi.wabenpay.config;

@Deprecated
public class WabenPayConfig {

	/**
	 * 商户号
	 */
	public static final String merchantNo = "888888888";
	/**
	 * 签名key
	 */
	public static final String key = "134fb0bf3bdd54ee9098f4cbc4351b9a";
	/**
	 * 证件类型
	 */
	public static final String idType = "identyty_card";
	/**
	 * 银行卡类型
	 */
	public static final String cardType = "cash_card";
	/**
	 * 交易类型
	 */
	public static final String tradeType = "quick_wap";
	/**
	 * 签名类型
	 */
	public static final String signType = "md5";
	/**
	 * 通知回调地址
	 */
	public static final String testNotifyUrl = "http://1108ab82.nat123.cc:53623/payment/quickpaynotify";
	public static final String prodNotifyUrl = "https://m.youguwang.com.cn/tactics/payment/quickpaynotify";

	public static String getMerchantno() {
		return merchantNo;
	}

	public static String getKey() {
		return key;
	}

	public static String getIdtype() {
		return idType;
	}

	public static String getCardtype() {
		return cardType;
	}

	public static String getSigntype() {
		return signType;
	}

}
