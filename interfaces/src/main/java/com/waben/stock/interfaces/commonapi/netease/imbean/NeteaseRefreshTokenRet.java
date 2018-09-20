package com.waben.stock.interfaces.commonapi.netease.imbean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseRefreshTokenRet {

	/**
	 * 网易云通信ID
	 * <p>
	 * 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * </p>
	 */
	private String accid;
	/**
	 * 网易云通信ID可以指定登录token值
	 * <p>
	 * 网易云通信ID可以指定登录token值，最大长度128字符
	 * </p>
	 */
	private String token;

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
