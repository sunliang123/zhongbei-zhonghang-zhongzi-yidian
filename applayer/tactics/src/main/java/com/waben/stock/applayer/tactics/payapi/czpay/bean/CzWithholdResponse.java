package com.waben.stock.applayer.tactics.payapi.czpay.bean;

public class CzWithholdResponse {

	private String respCode;

	private String respMsg;

	public CzWithholdResponse(String respCode, String respMsg) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public boolean successful() {
		return "0000".equals(respCode) || "1008".equals(respCode);
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

}
