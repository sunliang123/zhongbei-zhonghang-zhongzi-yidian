package com.waben.stock.interfaces.commonapi.netease.imbean;

public class NeteaseChatroomCreateParam {

	/**
	 * 聊天室属主的账号accid，必填
	 */
	private String creator;
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
	 * 队列管理权限，选填
	 * <ul>
	 * <li>0:所有人都有权限变更队列，默认0</li>
	 * <li>1:只有主播管理员才能操作变更</li>
	 * </ul>
	 */
	private int queuelevel;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public int getQueuelevel() {
		return queuelevel;
	}

	public void setQueuelevel(int queuelevel) {
		this.queuelevel = queuelevel;
	}

}
