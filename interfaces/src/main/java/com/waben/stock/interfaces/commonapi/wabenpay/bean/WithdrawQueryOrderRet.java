package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawQueryOrderRet {

	/**
	 * 状态
	 * <p>
	 * 1：待支付，2：已支付，99：提交上游失败
	 * </p>
	 */
	@JsonProperty("status")
	private int status;
	/**
	 * 应用id
	 */
	@JsonProperty("app_id")
	private String appId;
	/**
	 * 响应信息
	 */
	@JsonProperty("msg")
	private String msg;
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
	 */
	@JsonProperty("Sign")
	private String sign;
	/**
	 * 代付状态(3-处理中，4-支付成功，5-支付失败)
	 */
	@JsonProperty("pay_status")
	private int payStatus;
	/**
	 * 日期
	 */
	@JsonProperty("timestamp")
	private String timestamp;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
