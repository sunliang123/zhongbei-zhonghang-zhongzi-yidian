package com.waben.stock.interfaces.commonapi.wabenpay.bean;

public class CommonRet {
	/**
	 * 结果码
	 */
	private int code;
	/**
	 * 结果信息
	 */
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
