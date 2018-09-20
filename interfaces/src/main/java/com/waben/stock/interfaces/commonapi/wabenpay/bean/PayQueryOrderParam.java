package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayQueryOrderParam {

	/**
	 * 应用id
	 */
	@JsonProperty("app_id")
	private String appId;
	/**
	 * 签名
	 * <p>
	 * md5md5(AppId+appSecre+out_order_no+order_no)大写
	 * </p>
	 */
	@JsonProperty("sign")
	private String sign;
	/**
	 * 平台订单号
	 */
	@JsonProperty("order_no")
	private String orderNo;
	/**
	 * 商户订单号
	 */
	@JsonProperty("out_order_no")
	private String outOrderNo;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

}
