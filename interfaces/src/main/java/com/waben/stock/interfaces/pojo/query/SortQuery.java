package com.waben.stock.interfaces.pojo.query;

public class SortQuery {

	/**
	 * 排序字段
	 */
	private String field;
	/**
	 * 升降序，asc或者desc
	 */
	private String dir;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}
