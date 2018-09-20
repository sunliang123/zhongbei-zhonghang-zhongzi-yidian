package com.waben.stock.interfaces.commonapi.netease.imbean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseChatroomCreateRet {

	/**
	 * 房间ID
	 */
	private long roomid;
	/**
	 * 是否有效
	 */
	private boolean valid;
	/**
	 * 聊天室名称，必填
	 * <p>
	 * 长度限制128个字符
	 * </p>
	 */
	private String name;
	/**
	 * 公告，选填
	 * <p>
	 * 长度限制4096个字符
	 * </p>
	 */
	private String announcement;
	/**
	 * 直播地址，选填
	 * <p>
	 * 长度限制1024个字符
	 * </p>
	 */
	private String broadcasturl;
	/**
	 * 扩展字段，选填
	 * <p>
	 * 最长4096字符
	 * </p>
	 */
	private String ext;
	/**
	 * 聊天室属主的账号accid
	 */
	private String creator;

	public long getRoomid() {
		return roomid;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getBroadcasturl() {
		return broadcasturl;
	}

	public void setBroadcasturl(String broadcasturl) {
		this.broadcasturl = broadcasturl;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
