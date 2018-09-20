package com.waben.stock.interfaces.commonapi.netease.livebean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * 直播统一响应实体
 * 
 * @author luomengan
 *
 * @param <T>
 *            响应主信息泛型
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseLiveResponse<T> {

	private String msg;

	private String requestId;

	private int code;

	private T ret;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getRet() {
		return ret;
	}

	public void setRet(T ret) {
		this.ret = ret;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
