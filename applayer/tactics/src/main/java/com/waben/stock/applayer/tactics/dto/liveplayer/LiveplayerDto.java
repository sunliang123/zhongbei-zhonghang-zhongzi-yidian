package com.waben.stock.applayer.tactics.dto.liveplayer;

public class LiveplayerDto {

	/**
	 * http拉流地址
	 */
	private String httpPullUrl;
	/**
	 * hls拉流地址
	 */
	private String hlsPullUrl;
	/**
	 * rtmp拉流地址
	 */
	private String rtmpPullUrl;
	/**
	 * 房间号
	 */
	private long roomid;
	/**
	 * 房间地址
	 */
	private String roomAddress;
	/**
	 * 账号
	 */
	private String accid;
	/**
	 * token
	 */
	private String token;

	public String getRtmpPullUrl() {
		return rtmpPullUrl;
	}

	public void setRtmpPullUrl(String rtmpPullUrl) {
		this.rtmpPullUrl = rtmpPullUrl;
	}

	public String getHttpPullUrl() {
		return httpPullUrl;
	}

	public void setHttpPullUrl(String httpPullUrl) {
		this.httpPullUrl = httpPullUrl;
	}

	public String getHlsPullUrl() {
		return hlsPullUrl;
	}

	public void setHlsPullUrl(String hlsPullUrl) {
		this.hlsPullUrl = hlsPullUrl;
	}

	public long getRoomid() {
		return roomid;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	public String getRoomAddress() {
		return roomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
