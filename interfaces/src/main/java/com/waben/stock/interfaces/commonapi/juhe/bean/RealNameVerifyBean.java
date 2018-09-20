package com.waben.stock.interfaces.commonapi.juhe.bean;

public class RealNameVerifyBean {

	/**
	 * 真实姓名
	 */
	public String realname;
	/**
	 * 身份证号码
	 */
	private String idcard;
	/**
	 * 验证结果，1:匹配 2:不匹配
	 */
	private String res;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

}
