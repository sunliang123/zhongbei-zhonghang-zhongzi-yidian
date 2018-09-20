package com.waben.stock.applayer.strategist.payapi.wabenpay.bean;

public class MessageResponseBean {

	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 编码方式
	 */
	private String charset;
	/**
	 * 签名类型
	 */
	private String signType;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 流水号
	 */
	private String transactNo;
	/**
	 * 下单时间
	 */
	private String orderTime;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTransactNo() {
		return transactNo;
	}

	public void setTransactNo(String transactNo) {
		this.transactNo = transactNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

}
