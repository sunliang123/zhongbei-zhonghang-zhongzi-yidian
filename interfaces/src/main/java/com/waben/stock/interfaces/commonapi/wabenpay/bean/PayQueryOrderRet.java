package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayQueryOrderRet {

	/**
	 * 状态
	 * <p>
	 * 1：待支付，2：已支付，99：提交上游失败
	 * </p>
	 */
	@JsonProperty("status")
	private int status;
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
	/**
	 * 日期
	 */
	@JsonProperty("date")
	private String date;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
