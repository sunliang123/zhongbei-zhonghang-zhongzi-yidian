package com.waben.stock.interfaces.commonapi.netease.config;

/**
 * 网易云直播配置
 * 
 * @author luomengan
 *
 */
public class NeteaseLiveConfig {

	public static final String AppKey = "d0fc8a8448dabce125efce3dcc90c611";

	public static final String AppSecret = "05a13f09d796";
	
	public static final String CurrentLivePlayerKey = "current-live-player";
	/**
	 * 2.1创建频道
	 */
	public static final String CreateUrl = "https://vcloud.163.com/app/channel/create";
	/**
	 * 2.2修改频道
	 */
	public static final String UpdateUrl = "https://vcloud.163.com/app/channel/update";
	/**
	 * 2.3删除频道
	 */
	public static final String DeleteUrl = "https://vcloud.163.com/app/channel/delete";
	/**
	 * 2.4获取频道状态
	 */
	public static final String ChannelstatsUrl = "https://vcloud.163.com/app/channelstats";
	/**
	 * 2.5获取频道列表
	 */
	public static final String ChannellistUrl = "https://vcloud.163.com/app/channellist";
	/**
	 * 2.6重新获取推流地址
	 */
	public static final String AddressUrl = "https://vcloud.163.com/app/address";
	/**
	 * 2.7设置频道为录制状态
	 */
	public static final String SetAlwaysRecordUrl = "https://vcloud.163.com/app/channel/setAlwaysRecord";
	/**
	 * 2.8禁用频道
	 */
	public static final String DisableUrl = "https://vcloud.163.com/app/channel/pause";
	/**
	 * 2.20设置录制信息
	 */
	public static final String SetupRecordInfoUrl = "https://vcloud.163.com/app/channel/setupRecordInfo";

}
