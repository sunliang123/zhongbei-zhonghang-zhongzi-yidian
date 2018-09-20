package com.waben.stock.interfaces.commonapi.netease.livebean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeteaseAddressRet {

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
