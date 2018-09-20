package com.waben.stock.interfaces.commonapi.netease.livebean;

public class NeteaseChannellistParam {

	/**
	 * 单页记录数，默认值为10 否
	 */
	private int records;
	/**
	 * 要取第几页，默认值为1 否
	 */
	private int pnum;
	/**
	 * 排序的域，支持的排序域为：ctime（默认） 否
	 */
	private String ofield;
	/**
	 * 升序还是降序，1升序，0降序，默认为desc 否
	 */
	private int sort;
	/**
	 * 筛选频道状态，status取值：（0：空闲,1：直播，2：禁用，3：录制中） 否
	 */
	private int status;

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getOfield() {
		return ofield;
	}

	public void setOfield(String ofield) {
		this.ofield = ofield;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
