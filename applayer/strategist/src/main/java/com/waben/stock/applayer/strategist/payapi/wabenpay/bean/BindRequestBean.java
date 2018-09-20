package com.waben.stock.applayer.strategist.payapi.wabenpay.bean;

import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WabenPayConfig;

public class BindRequestBean {
	/**
	 * 商户号
	 */
	private String merchantNo = WabenPayConfig.merchantNo;
	/**
	 * 商户ID
	 */
	private String member;
	/**
	 * 证件类型
	 */
	private String idType = WabenPayConfig.idType;
	/**
	 * 身份证号
	 */
	private String idNo;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 电话号
	 */
	private String phone;
	/**
	 * 银行卡号
	 */
	private String cardNo;
	/**
	 * 银行卡类型
	 */
	private String cardType = WabenPayConfig.cardType;
	/**
	 * 银行代号
	 */
	private String bankCode;
	/**
	 * 签名类型
	 */
	private String signType = WabenPayConfig.signType;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
