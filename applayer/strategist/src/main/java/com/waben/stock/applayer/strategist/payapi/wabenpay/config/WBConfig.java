package com.waben.stock.applayer.strategist.payapi.wabenpay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WBConfig {

	@Value("${pay.merchant}")
	private String merchantNo;
	@Value("${pay.key}")
	private String key;
	@Value("${pay.notifyUrl}")
	private String notifyUrl;
	@Value("${pay.frontUrl}")
	private String frontUrl;
	@Value("${pay.h5ProxyfrontUrl}")
	private String h5ProxyfrontUrl;
	@Value("${pay.h5FrontUrl}")
	private String h5FrontUrl;
	@Value("${pay.protocolcallback}")
	private String protocol_callback;

	@Value("${pay.unionpayMerchant}")
	private String unionpayMerchant;
	@Value("${pay.unionpayKey}")
	private String unionpayKey;
	@Value("${pay.unionpaynotifyUrl}")
	private String unionpayNotifyUrl;
	@Value("${pay.unionpaytempfrontUrl}")
	private String unionpayTempFrontUrl;
	@Value("${pay.unionpayfrontUrl}")
	private String unionpayFrontUrl;

	public static final String tradeType = "quick_bank";
	public static final String platformType = "platform_wap";
	public static final String quick_bank_url = "http://b.waben.com.cn/api/quick/bankpay";
	public static final String domain = "http://b.waben.com.cn";
	public static final String protocol_url = "http://b.waben.com.cn/api/protocol/pay";
	public static final String sign_type = "md5";
	public static final String card_type = "cash_card";
	public static final String protocol_type = "protocol_wap";

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getProtocol_callback() {
		return protocol_callback;
	}

	public void setProtocol_callback(String protocol_callback) {
		this.protocol_callback = protocol_callback;
	}

	public String getH5FrontUrl() {
		return h5FrontUrl;
	}

	public void setH5FrontUrl(String h5FrontUrl) {
		this.h5FrontUrl = h5FrontUrl;
	}

	public String getH5ProxyfrontUrl() {
		return h5ProxyfrontUrl;
	}

	public void setH5ProxyfrontUrl(String h5ProxyfrontUrl) {
		this.h5ProxyfrontUrl = h5ProxyfrontUrl;
	}

	public String getUnionpayNotifyUrl() {
		return unionpayNotifyUrl;
	}

	public void setUnionpayNotifyUrl(String unionpayNotifyUrl) {
		this.unionpayNotifyUrl = unionpayNotifyUrl;
	}

	public String getUnionpayFrontUrl() {
		return unionpayFrontUrl;
	}

	public void setUnionpayFrontUrl(String unionpayFrontUrl) {
		this.unionpayFrontUrl = unionpayFrontUrl;
	}

	public String getUnionpayTempFrontUrl() {
		return unionpayTempFrontUrl;
	}

	public void setUnionpayTempFrontUrl(String unionpayTempFrontUrl) {
		this.unionpayTempFrontUrl = unionpayTempFrontUrl;
	}

	public String getUnionpayMerchant() {
		return unionpayMerchant;
	}

	public void setUnionpayMerchant(String unionpayMerchant) {
		this.unionpayMerchant = unionpayMerchant;
	}

	public String getUnionpayKey() {
		return unionpayKey;
	}

	public void setUnionpayKey(String unionpayKey) {
		this.unionpayKey = unionpayKey;
	}

}
