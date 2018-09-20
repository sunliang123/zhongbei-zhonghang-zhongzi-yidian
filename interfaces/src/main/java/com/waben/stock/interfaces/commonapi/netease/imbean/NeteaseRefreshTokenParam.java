package com.waben.stock.interfaces.commonapi.netease.imbean;

public class NeteaseRefreshTokenParam {

	/**
	 * 网易云通信ID
	 * <p>
	 * 网易云通信ID，最大长度32字符，必须保证一个 APP内唯一
	 * </p>
	 */
	private String accid;

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

}
