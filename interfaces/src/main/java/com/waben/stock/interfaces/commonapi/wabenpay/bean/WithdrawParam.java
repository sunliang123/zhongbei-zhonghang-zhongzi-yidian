package com.waben.stock.interfaces.commonapi.wabenpay.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawParam {
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
	 * 银行账号姓名
	 */
	@JsonProperty("bank_acct_name")
	private String bankAcctName;
	/**
	 * 银行账号
	 */
	@JsonProperty("bank_no")
	private String bankNo;
	/**
	 * 卡类型
	 */
	@JsonProperty("card_type")
	private String cardType;
	/**
	 * 银行编码
	 */
	@JsonProperty("bank_code")
	private String bankCode;
	/**
	 * 银行名称
	 */
	@JsonProperty("bank_name")
	private String bankName;
	/**
	 * 时间戳
	 */
	@JsonProperty("timestamp")
	private String timestamp;
	/**
	 * 版本号
	 */
	@JsonProperty("version")
	private String version;
	/**
	 * 代付金额
	 */
	@JsonProperty("total_amt")
	private BigDecimal totalAmt;

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

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

}
