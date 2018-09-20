package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawQueryOrderParam {

	/**
	 * 应用id
	 */
	@JsonProperty("app_id")
	private String appId;
	/**
	 * 签名
	 * <p>
	 * md5(AppId+appSecre+timestamp+out_order_no)
	 * </p>
	 */
	@JsonProperty("sign")
	private String sign;
	/**
	 * 商户订单号
	 */
	@JsonProperty("out_order_no")
	private String outOrderNo;
	/**
	 * 时间戳
	 */
	@JsonProperty("timestamp")
	private String timestamp;

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

}
