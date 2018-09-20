package com.waben.stock.applayer.strategist.rabbitmq.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayQueryMessage {

	/**
	 * 应用id
	 */
	private String appId;
	/**
	 * 平台订单号
	 */
	private String orderNo;
	/**
	 * 商户订单号
	 */
	private String outOrderNo;
	/**
	 * 当前消息消费次数
	 */
	private int consumeCount;

	public PayQueryMessage() {
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public int getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

}
