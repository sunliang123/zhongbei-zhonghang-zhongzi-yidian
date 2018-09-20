package com.waben.stock.interfaces.commonapi.netease.livebean;

public class NeteaseCreateParam {

	private String name;

	private int type = 0;

	public NeteaseCreateParam(String name) {
		super();
		this.name = name;
	}

	public NeteaseCreateParam() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
