package com.waben.stock.applayer.tactics.payapi.wabenpay.bean;

import com.waben.stock.applayer.tactics.payapi.wabenpay.config.WabenPayConfig;

public class PayRequestBean {

	private String merchantNo = WabenPayConfig.merchantNo;

	private String tradeType = WabenPayConfig.tradeType;

	private String outTradeNo;

	private String notifyUrl;

	private String amount;

	private String timeStart;

	private String contractNo;

	private String bankAccount;

	private String validaCode;

	private String transactNo;

	private String signType = WabenPayConfig.signType;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getValidaCode() {
		return validaCode;
	}

	public void setValidaCode(String validaCode) {
		this.validaCode = validaCode;
	}

	public String getTransactNo() {
		return transactNo;
	}

	public void setTransactNo(String transactNo) {
		this.transactNo = transactNo;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
