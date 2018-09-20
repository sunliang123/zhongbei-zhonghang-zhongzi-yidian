package com.waben.stock.interfaces.commonapi.netease.livebean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseChannelstatsRet {

	/**
	 * 创建频道的时间戳
	 */
	private Long ctime;
	/**
	 * 频道ID，32位字符串
	 */
	private String cid;
	/**
	 * 频道名称
	 */
	private String name;
	/**
	 * 频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
	 */
	private int status;
	/**
	 * 频道类型 ( 0 : rtmp, 1 : hls, 2 : http)
	 */
	private int type;
	/**
	 * 用户ID，是用户在网易云视频与通信业务的标识，用于与其他用户的业务进行区分。通常，用户不需关注和使用。
	 */
	private Long uid;
	/**
	 * 1-开启录制； 0-关闭录制
	 */
	private int needRecord;
	/**
	 * 1-flv； 0-mp4
	 */
	private int format;
	/**
	 * 录制切片时长(分钟)，默认120分钟
	 */
	private int duration;
	/**
	 * 录制后文件名
	 */
	private String filename;
	/**
	 * 网易云内部维护用字段，用户不需关注。后续版本将删除，请勿调用
	 */
	private String recordStatus;

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

}
