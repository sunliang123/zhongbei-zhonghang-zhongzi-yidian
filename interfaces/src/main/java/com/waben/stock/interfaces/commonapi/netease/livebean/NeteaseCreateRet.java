package com.waben.stock.interfaces.commonapi.netease.livebean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseCreateRet {

	/**
	 * 频道ID，32位字符串
	 */
	private String cid;
	/**
	 * 创建频道的时间戳
	 */
	private Long ctime;
	/**
	 * 频道名称
	 */
	private String name;
	/**
	 * 推流地址
	 */
	private String pushUrl;
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
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

	public String getRtmpPullUrl() {
		return rtmpPullUrl;
	}

	public void setRtmpPullUrl(String rtmpPullUrl) {
		this.rtmpPullUrl = rtmpPullUrl;
	}

}
