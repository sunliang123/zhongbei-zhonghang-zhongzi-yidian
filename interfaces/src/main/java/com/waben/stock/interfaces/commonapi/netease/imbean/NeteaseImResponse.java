package com.waben.stock.interfaces.commonapi.netease.imbean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * IM统一响应实体
 * 
 * @author luomengan
 *
 * @param <T>
 *            响应主信息泛型
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseImResponse<T> {

	private String msg;

	private int code;

	private T ret;

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
