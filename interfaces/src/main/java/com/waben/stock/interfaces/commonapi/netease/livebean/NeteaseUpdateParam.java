package com.waben.stock.interfaces.commonapi.netease.livebean;

public class NeteaseUpdateParam {

	private String cid;

	private String name;

	private int type = 0;

	public NeteaseUpdateParam() {
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
