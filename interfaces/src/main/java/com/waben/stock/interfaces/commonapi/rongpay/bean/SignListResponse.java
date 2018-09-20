package com.waben.stock.interfaces.commonapi.rongpay.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignListResponse {

	@JsonProperty("member_id")
	private String memberId;

	@JsonProperty("merchant_id")
	private String merchantId;

	@JsonProperty("result_code")
	private String resultCode;

	@JsonProperty("sign")
	private String sign;

	@JsonProperty("merchant_sign_list")
	private List<SignInfo> merchantSignList;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<SignInfo> getMerchantSignList() {
		return merchantSignList;
	}

	public void setMerchantSignList(List<SignInfo> merchantSignList) {
		this.merchantSignList = merchantSignList;
	}

}
