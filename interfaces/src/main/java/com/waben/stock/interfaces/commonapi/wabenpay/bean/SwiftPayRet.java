package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwiftPayRet extends CommonRet {
	/**
	 * 接入方订单id
	 */
	@JsonProperty("out_order_no")
	private String outOrderNo;
	/**
	 * 平台订单号
	 */
	@JsonProperty("trade_no")
	private String tradeNo;
	/**
	 * 支付金额
	 */
	@JsonProperty("balance")
	private BigDecimal balance;
	/**
	 * 支付类型
	 */
	@JsonProperty("payType")
	private String payType;
	/**
	 * 支付类型
	 */
	@JsonProperty("typeName")
	private String typeName;
	/**
	 * 支付链接
	 */
	@JsonProperty("payUrl")
	private String payUrl;
	/**
	 * 令牌
	 */
	@JsonProperty("token")
	private String token;

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
