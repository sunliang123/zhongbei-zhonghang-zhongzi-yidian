package com.waben.stock.applayer.tactics.payapi.wabenpay.bean;

public class PayNotifyBean {
	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 上送订单号
	 */
	private String outTradeNo;
	/**
	 * 支付金额
	 */
	private String amount;
	/**
	 * 交易时间
	 */
	private String transactTime;
	/**
	 * 交易订单号
	 */
	private String transactNo;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 验签字段
	 */
	private String sign;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getTransactNo() {
		return transactNo;
	}

	public void setTransactNo(String transactNo) {
		this.transactNo = transactNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
