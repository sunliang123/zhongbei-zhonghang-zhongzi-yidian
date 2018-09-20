package com.waben.stock.applayer.tactics.rabbitmq.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawQueryMessage {

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
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 当前消息消费次数
	 */
	private int consumeCount;

	public WithdrawQueryMessage() {
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}

}
