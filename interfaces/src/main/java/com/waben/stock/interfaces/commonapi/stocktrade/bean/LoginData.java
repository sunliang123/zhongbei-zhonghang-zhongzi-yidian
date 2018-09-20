package com.waben.stock.interfaces.commonapi.stocktrade.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 登陆结果
 * 
 * @author luomengan
 *
 */
public class LoginData {

	/**
	 * 客户姓名
	 */
	@JsonProperty("client_name")
	private String clientName;
	/**
	 * 账号会话信息
	 */
	@JsonProperty("fund_account")
	private String fundAccount;
	/**
	 * 资金账号信息
	 */
	@JsonProperty("token")
	private String token;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
