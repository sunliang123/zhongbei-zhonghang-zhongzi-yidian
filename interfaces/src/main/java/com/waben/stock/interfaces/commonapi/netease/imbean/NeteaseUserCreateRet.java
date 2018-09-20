package com.waben.stock.interfaces.commonapi.netease.imbean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseUserCreateRet {

	/**
	 * 网易云通信ID
	 * <p>
	 * 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * </p>
	 */
	private String accid;
	/**
	 * 网易云通信ID昵称，选填
	 * <p>
	 * 网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
	 * </p>
	 */
	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
