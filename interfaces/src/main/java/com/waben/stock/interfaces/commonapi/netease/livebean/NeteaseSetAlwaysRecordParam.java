package com.waben.stock.interfaces.commonapi.netease.livebean;

public class NeteaseSetAlwaysRecordParam {

	/**
	 * 频道ID，32位字符串 是
	 */
	private String cid;
	/**
	 * 1-开启录制； 0-关闭录制 是
	 */
	private int needRecord;
	/**
	 * 1-flv； 0-mp4 是
	 */
	private int format;
	/**
	 * 录制切片时长(分钟)，5~120分钟 是
	 */
	private int duration;
	/**
	 * 录制后文件名（只支持中文、字母和数字），格式为filename_YYYYMMDD-HHmmssYYYYMMDD-HHmmss,
	 * 文件名录制起始时间（年月日时分秒) -录制结束时间（年月日时分秒) 否
	 */
	private String filename;

	public NeteaseSetAlwaysRecordParam() {
		super();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getNeedRecord() {
		return needRecord;
	}

	public void setNeedRecord(int needRecord) {
		this.needRecord = needRecord;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
