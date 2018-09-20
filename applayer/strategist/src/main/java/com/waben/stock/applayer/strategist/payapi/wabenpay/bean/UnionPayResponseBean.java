package com.waben.stock.applayer.strategist.payapi.wabenpay.bean;

public class UnionPayResponseBean {

	/**
	 * 商户号
	 */
	private String merchantNo;
	/**
	 * 字符集
	 */
	private String charset;
	/**
	 * 加密方式
	 */
	private String signType;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 下单时间
	 */
	private String orderTime;
	/**
	 * 支付系统订单号
	 */
	private String transNo;
	/**
	 * 订单号
	 */
	private String outTradeNo;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 跳转地址
	 */
	private String redirectURL;

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

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

}
