package com.waben.stock.applayer.tactics.payapi.wabenpay.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayResponseBean {
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
	 * 支付类型
	 */
	private String tradeType;
	/**
	 * 订单时间
	 */
	private String orderTime;
	/**
	 * 订单号
	 */
	private String transNo;
	/**
	 * 上送订单号
	 */
	private String outTradeNo;
	/**
	 * 订单状态
	 */
	private String status;

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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
