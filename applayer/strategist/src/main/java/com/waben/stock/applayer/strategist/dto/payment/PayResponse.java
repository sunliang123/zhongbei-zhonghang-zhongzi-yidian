package com.waben.stock.applayer.strategist.dto.payment;

public class PayResponse {

	private String paymentNo;

	private String fowardUrl;

	public String getFowardUrl() {
		return fowardUrl;
	}

	public void setFowardUrl(String fowardUrl) {
		this.fowardUrl = fowardUrl;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

}
