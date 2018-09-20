package com.waben.stock.interfaces.commonapi.netease;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.waben.stock.interfaces.commonapi.netease.config.NeteaseLiveConfig;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseChannellistParam;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseChannellistRet;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseLivePage;
import com.waben.stock.interfaces.commonapi.netease.livebean.NeteaseLiveResponse;
import com.waben.stock.interfaces.commonapi.netease.util.CheckSumBuilder;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.RandomUtil;

/**
 * 直播频道统一请求网易云
 * 
 * @author luomengan
 *
 */
public class ChannelManageOverHttps {

	public static RestTemplate restTemplate = new RestTemplate();

	private static Logger logger = LoggerFactory.getLogger(ChannelManageOverHttps.class);

	/**
	 * 统一请求网易云直播
	 */
	public static <T> NeteaseLiveResponse<T> doAction(String requestUrl, Object param, Class<T> clazz) {
		// 设置请求头
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("AppKey", NeteaseLiveConfig.AppKey);
		String nonce = RandomUtil.generateNonceStr();
		requestHeaders.set("Nonce", nonce);
		String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
		requestHeaders.set("CurTime", curTime);
		requestHeaders.set("CheckSum", CheckSumBuilder.getCheckSum(NeteaseLiveConfig.AppSecret, nonce, curTime));
		requestHeaders.set("Content-Type", "application/json;charset=utf-8");
		// 请求对象
		String paramStr = JacksonUtil.encode(param);
		logger.info("请求网易云信直播接口:" + requestUrl);
		logger.info("请求网易云信直播接口数据:" + paramStr);
		HttpEntity<String> requestEntity = new HttpEntity<String>(JacksonUtil.encode(param), requestHeaders);
		// 发送请求
		String resultJson = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网易云信直播接口响应:" + resultJson);
		// 响应对象
		NeteaseLiveResponse<T> responseObj = JacksonUtil.decode(resultJson,
				JacksonUtil.getGenericType(NeteaseLiveResponse.class, clazz));
		return responseObj;
	}

	/**
	 * 统一请求网易云直播
	 */
	public static <T> NeteaseLiveResponse<T> doAction(String requestUrl, Object param, JavaType javaType) {
		// 设置请求头
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("AppKey", NeteaseLiveConfig.AppKey);
		String nonce = RandomUtil.generateNonceStr();
		requestHeaders.set("Nonce", nonce);
		String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
		requestHeaders.set("CurTime", curTime);
		requestHeaders.set("CheckSum", CheckSumBuilder.getCheckSum(NeteaseLiveConfig.AppSecret, nonce, curTime));
		requestHeaders.set("Content-Type", "application/json;charset=utf-8");
		// 请求对象
		HttpEntity<String> requestEntity = new HttpEntity<String>(JacksonUtil.encode(param), requestHeaders);
		// 发送请求
		String resultJson = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网易云信直播响应:" + resultJson);
		// 响应对象
		NeteaseLiveResponse<T> responseObj = JacksonUtil.decode(resultJson,
				JacksonUtil.getGenericType(NeteaseLiveResponse.class, javaType));
		return responseObj;
	}

	public static void testMain(String[] args) {
		// 创建频道
		// NeteaseCreateParam createParam = new NeteaseCreateParam("测试频道");
		// NeteaseResponse<NeteaseCreateRet> createResponse =
		// doAction(NeteaseConfig.CreateUrl, createParam,
		// NeteaseCreateRet.class);
		// System.out.println("创建频道响应结果:" + JacksonUtil.encode(createResponse));

		// 获取频道列表
		NeteaseChannellistParam listParam = new NeteaseChannellistParam();
		JavaType listJavaType = JacksonUtil.getGenericType(NeteaseLivePage.class, NeteaseChannellistRet.class);
		NeteaseLiveResponse<NeteaseLivePage<NeteaseChannellistRet>> listResponse = doAction(
				NeteaseLiveConfig.ChannellistUrl, listParam, listJavaType);
		System.out.println("获取频道列表响应结果:" + JacksonUtil.encode(listResponse));

		// 重新获取推流地址
		// NeteaseAddressParam addressParam = new NeteaseAddressParam();
		// addressParam.setCid("0b55c14db41d4eda898f87fe4f7a47d8");
		// NeteaseResponse<NeteaseAddressRet> addressResponse =
		// doAction(NeteaseConfig.AddressUrl, addressParam,
		// NeteaseAddressRet.class);
		// System.out.println("获取频道列表响应结果:" +
		// JacksonUtil.encode(addressResponse));
	}

}
