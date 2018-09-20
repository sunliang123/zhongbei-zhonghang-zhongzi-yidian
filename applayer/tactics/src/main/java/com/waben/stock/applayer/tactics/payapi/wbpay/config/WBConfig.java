package com.waben.stock.applayer.tactics.payapi.wbpay.config;

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

	@Value("${pay.rongH5PayAddress}")
	private String rongH5PayAddress;
	@Value("${pay.rontSellerEmail}")
	private String rontSellerEmail;
	@Value("${pay.rongMerchant}")
	private String rongMerchantNo;
	@Value("${pay.rongKey}")
	private String rongKey;
	@Value("${pay.rongOrderPrefix}")
	private String rongOrderPrefix;
	@Value("${pay.rongNotifyUrl}")
	private String rongNotifyUrl;
	@Value("${pay.rongSecondaryPayBaseUrl}")
	private String rongSecondaryPayBaseUrl;
	@Value("${pay.rongPublicKeyPath}")
	private String rongPublicKeyPath;
	@Value("${pay.rongPrivateKeyPath}")
	private String rongPrivateKeyPath;
	@Value("${pay.rongEncryptPwd}")
	private String rongEncryptPwd;
	/** 大额快捷支付基础路径 */
	@Value("${pay.rongLargeAmountBaseUrl}")
	private String rongLargeAmountBaseUrl;
	/** 大额快捷签约页面 */
	@Value("${pay.rongLargeAmountSignPage}")
	private String rongLargeAmountSignPage;
	/** 大额快捷支付页面 */
	@Value("${pay.rongLargeAmountPayPage}")
	private String rongLargeAmountPayPage;
	/** 大额快捷支付回调地址 */
	@Value("${pay.rongLargeAmountPayNotifyUrl}")
	private String rongLargeAmountPayNotifyUrl;

	// public static final String tradeType = "quick_bank";
	public static final String tradeType = "quick_wap";
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

	public String getRongMerchantNo() {
		return rongMerchantNo;
	}

	public void setRongMerchantNo(String rongMerchantNo) {
		this.rongMerchantNo = rongMerchantNo;
	}

	public String getRongKey() {
		return rongKey;
	}

	public void setRongKey(String rongKey) {
		this.rongKey = rongKey;
	}

	public String getRongNotifyUrl() {
		return rongNotifyUrl;
	}

	public void setRongNotifyUrl(String rongNotifyUrl) {
		this.rongNotifyUrl = rongNotifyUrl;
	}

	public String getRongSecondaryPayBaseUrl() {
		return rongSecondaryPayBaseUrl;
	}

	public void setRongSecondaryPayBaseUrl(String rongSecondaryPayBaseUrl) {
		this.rongSecondaryPayBaseUrl = rongSecondaryPayBaseUrl;
	}

	public String getRongH5PayAddress() {
		return rongH5PayAddress;
	}

	public void setRongH5PayAddress(String rongH5PayAddress) {
		this.rongH5PayAddress = rongH5PayAddress;
	}

	public String getRontSellerEmail() {
		return rontSellerEmail;
	}

	public void setRontSellerEmail(String rontSellerEmail) {
		this.rontSellerEmail = rontSellerEmail;
	}

	public String getRongEncryptPwd() {
		return rongEncryptPwd;
	}

	public void setRongEncryptPwd(String rongEncryptPwd) {
		this.rongEncryptPwd = rongEncryptPwd;
	}

	public String getRongPrivateKeyPath() {
		return rongPrivateKeyPath;
	}

	public void setRongPrivateKeyPath(String rongPrivateKeyPath) {
		this.rongPrivateKeyPath = rongPrivateKeyPath;
	}

	public String getRongPublicKeyPath() {
		return rongPublicKeyPath;
	}

	public void setRongPublicKeyPath(String rongPublicKeyPath) {
		this.rongPublicKeyPath = rongPublicKeyPath;
	}

	public String getRongOrderPrefix() {
		return rongOrderPrefix;
	}

	public void setRongOrderPrefix(String rongOrderPrefix) {
		this.rongOrderPrefix = rongOrderPrefix;
	}

	public String getRongLargeAmountSignPage() {
		return rongLargeAmountSignPage;
	}

	public void setRongLargeAmountSignPage(String rongLargeAmountSignPage) {
		this.rongLargeAmountSignPage = rongLargeAmountSignPage;
	}

	public String getRongLargeAmountPayPage() {
		return rongLargeAmountPayPage;
	}

	public void setRongLargeAmountPayPage(String rongLargeAmountPayPage) {
		this.rongLargeAmountPayPage = rongLargeAmountPayPage;
	}

	public String getRongLargeAmountPayNotifyUrl() {
		return rongLargeAmountPayNotifyUrl;
	}

	public void setRongLargeAmountPayNotifyUrl(String rongLargeAmountPayNotifyUrl) {
		this.rongLargeAmountPayNotifyUrl = rongLargeAmountPayNotifyUrl;
	}

	public String getRongLargeAmountBaseUrl() {
		return rongLargeAmountBaseUrl;
	}

	public void setRongLargeAmountBaseUrl(String rongLargeAmountBaseUrl) {
		this.rongLargeAmountBaseUrl = rongLargeAmountBaseUrl;
	}

}
