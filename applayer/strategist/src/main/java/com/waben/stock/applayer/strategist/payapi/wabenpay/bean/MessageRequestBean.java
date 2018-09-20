package com.waben.stock.applayer.strategist.payapi.wabenpay.bean;

import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WabenPayConfig;

public class MessageRequestBean {
	/**
	 * 商户号
	 */
	private String merchantNo = WabenPayConfig.merchantNo;
	/**
	 * 绑卡协议号
	 */
	private String contractNo;
	/**
	 * 商户编号
	 */
	private String member;
	/**
	 * 支付金额
	 */
	private String amount;
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

}
