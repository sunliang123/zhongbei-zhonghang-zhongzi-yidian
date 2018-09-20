package com.waben.stock.applayer.tactics.payapi.wabenpay.bean;

public class BindResponseBean {
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
	 * 对应的支付平台编号
	 */
	private String contractNo;

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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}
