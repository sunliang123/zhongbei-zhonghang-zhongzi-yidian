package com.waben.stock.interfaces.commonapi.netease.livebean;

import java.util.List;

public class NeteaseLivePage<T> {

	private int pnum;

	private List<T> list;

	private long totalRecords;

	private int totalPnum;

	private int records;

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPnum() {
		return totalPnum;
	}

	public void setTotalPnum(int totalPnum) {
		this.totalPnum = totalPnum;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

}
