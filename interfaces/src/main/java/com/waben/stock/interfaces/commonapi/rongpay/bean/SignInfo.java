package com.waben.stock.interfaces.commonapi.rongpay.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignInfo {

	private String bankCardType;

	private String bankCode;

	private String bankName;

	private String cardNoX;

	private String phoneX;

	private String signNo;

	private String signStatus;

	public String getBankCardType() {
		return bankCardType;
	}

	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNoX() {
		return cardNoX;
	}

	public void setCardNoX(String cardNoX) {
		this.cardNoX = cardNoX;
	}

	public String getPhoneX() {
		return phoneX;
	}

	public void setPhoneX(String phoneX) {
		this.phoneX = phoneX;
	}

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

}
