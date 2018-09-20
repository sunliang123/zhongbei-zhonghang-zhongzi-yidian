package com.waben.stock.interfaces.commonapi.stocktrade.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeMessage {

	/**
	 * 信息
	 */
	@JsonProperty("error_info")
	private String errorInfo;
	/**
	 * 代码
	 */
	@JsonProperty("error_no")
	private String errorNo;

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}

}
