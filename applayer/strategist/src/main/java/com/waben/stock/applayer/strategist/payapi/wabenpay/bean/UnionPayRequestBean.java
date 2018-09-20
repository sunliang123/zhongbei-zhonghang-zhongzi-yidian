package com.waben.stock.applayer.strategist.payapi.wabenpay.bean;

import java.math.BigDecimal;

public class UnionPayRequestBean {

	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 异步通知地址
	 */
	private String notifyUrl;
	/**
	 * 支付完成跳转页面
	 */
	private String frontUrl;
	/**
	 * 金额（单位分）
	 */
	private String amount;
	/**
	 * 发起时间
	 */
	private String timeStart;
	/**
	 * 订单号
	 */
	private String outTradeNo;
	/**
	 * 银行编号
	 */
	private String bankCode;
	/**
	 * 交易类型
	 */
	private String tradeType = "netbank_page";
	/**
	 * 支付方式
	 */
	private String payment = "bank";
	/**
	 * 加密方式
	 */
	private String signType = "md5";

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

}
