package com.waben.stock.applayer.tactics.dto.payment;

import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;

public class AliturnPaymentOrderDto extends PaymentOrderDto {

	private String forward = "HTTPS://QR.ALIPAY.COM/FKX02949VQTZFUX1UQ5NC8";

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

}
