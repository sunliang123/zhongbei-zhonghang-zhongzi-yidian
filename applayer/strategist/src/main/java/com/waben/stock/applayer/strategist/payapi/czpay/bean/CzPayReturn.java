package com.waben.stock.applayer.strategist.payapi.czpay.bean;

public class CzPayReturn {

	private String sendSeqId;

	private String respCode;

	private String respDesc;

	private String mac;

	public String getSendSeqId() {
		return sendSeqId;
	}

	public void setSendSeqId(String sendSeqId) {
		this.sendSeqId = sendSeqId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
