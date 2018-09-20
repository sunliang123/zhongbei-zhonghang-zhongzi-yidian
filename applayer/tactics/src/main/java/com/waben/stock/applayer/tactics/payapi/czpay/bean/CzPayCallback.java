package com.waben.stock.applayer.tactics.payapi.czpay.bean;

public class CzPayCallback {

	private String fee;

	private String orgSendSeqId;

	private String payResult;

	private String payDesc;

	private String mac;

	private String organizationId;

	private String transAmt;

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getOrgSendSeqId() {
		return orgSendSeqId;
	}

	public void setOrgSendSeqId(String orgSendSeqId) {
		this.orgSendSeqId = orgSendSeqId;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

}
