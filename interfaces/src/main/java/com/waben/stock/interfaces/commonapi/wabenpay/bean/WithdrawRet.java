package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawRet {

	/**
	 * 状态
	 */
	@JsonProperty("status")
	private int status;
	/**
	 * 响应信息
	 */
	@JsonProperty("msg")
	private String msg;
	/**
	 * 应用id
	 */
	@JsonProperty("App_id")
	private String appId;
	/**
	 * 代付金额
	 */
	@JsonProperty("total_amt")
	private BigDecimal totalAmt;
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
	 * 签名
	 * <p>
	 * md5(app_id+app_secret+timestamp+out_order_no)
	 * </p>
	 */
	@JsonProperty("sign")
	private String sign;
	/**
	 * 时间戳
	 */
	@JsonProperty("timestamp")
	private String timestamp;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
