package com.waben.stock.interfaces.commonapi.netease;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.waben.stock.interfaces.commonapi.netease.config.NeteaseImConfig;
import com.waben.stock.interfaces.commonapi.netease.config.NeteaseLiveConfig;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseChatroomCreateParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseChatroomCreateRet;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseChatroomRequestAddrParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseImResponse;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseRefreshTokenParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseRefreshTokenRet;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseUserCreateParam;
import com.waben.stock.interfaces.commonapi.netease.imbean.NeteaseUserCreateRet;
import com.waben.stock.interfaces.commonapi.netease.util.CheckSumBuilder;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.RandomUtil;

/**
 * Im即时通讯统一请求网易云
 * 
 * @author luomengan
 *
 */
public class ImOverHttps {

	public static RestTemplate restTemplate = new RestTemplate();

	private static Logger logger = LoggerFactory.getLogger(ChannelManageOverHttps.class);

	/**
	 * 统一请求网易云IM
	 */
	public static <T> NeteaseImResponse<T> doAction(String requestUrl, Object param, String retKey, Class<T> clazz) {
		// 设置请求头
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("AppKey", NeteaseLiveConfig.AppKey);
		String nonce = RandomUtil.generateNonceStr();
		requestHeaders.set("Nonce", nonce);
		String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
		requestHeaders.set("CurTime", curTime);
		requestHeaders.set("CheckSum", CheckSumBuilder.getCheckSum(NeteaseLiveConfig.AppSecret, nonce, curTime));
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		// 请求对象
		String paramStr = objectToQueryString(param);
		logger.info("请求网易云信IM接口:" + requestUrl);
		logger.info("请求网易云信IM接口数据:" + paramStr);
		HttpEntity<String> requestEntity = new HttpEntity<String>(paramStr, requestHeaders);
		// 发送请求
		String resultJson = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网易云信IM接口响应:" + resultJson);
		// 响应对象
		try {
			ObjectNode objectNode = JacksonUtil.objectMapper.createObjectNode();
			JsonNode jsonNode = JacksonUtil.objectMapper.readTree(resultJson);
			int code = jsonNode.get("code").asInt();
			String msg = null;
			if (jsonNode.get("desc") != null) {
				msg = jsonNode.get("desc").asText();
			}
			objectNode.put("code", code);
			objectNode.put("msg", msg);
			objectNode.set("ret", jsonNode.get(retKey));
			NeteaseImResponse<T> responseObj = JacksonUtil.decode(objectNode.toString(),
					JacksonUtil.getGenericType(NeteaseImResponse.class, clazz));
			return responseObj;
		} catch (IOException e) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "请求网易云信IM接口返回的json解析异常");
		}
	}

	public static <T> NeteaseImResponse<T> doAction(String requestUrl, Object param, String retKey, JavaType javaType) {
		// 设置请求头
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("AppKey", NeteaseLiveConfig.AppKey);
		String nonce = RandomUtil.generateNonceStr();
		requestHeaders.set("Nonce", nonce);
		String curTime = String.valueOf(System.currentTimeMillis() / 1000L);
		requestHeaders.set("CurTime", curTime);
		requestHeaders.set("CheckSum", CheckSumBuilder.getCheckSum(NeteaseLiveConfig.AppSecret, nonce, curTime));
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		// 请求对象
		String paramStr = objectToQueryString(param);
		logger.info("请求网易云信IM接口数据:" + paramStr);
		HttpEntity<String> requestEntity = new HttpEntity<String>(paramStr, requestHeaders);
		// 发送请求
		String resultJson = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网易云信IM接口响应:" + resultJson);
		// 响应对象
		try {
			ObjectNode objectNode = JacksonUtil.objectMapper.createObjectNode();
			JsonNode jsonNode = JacksonUtil.objectMapper.readTree(resultJson);
			int code = jsonNode.get("code").asInt();
			String msg = null;
			if (jsonNode.get("desc") != null) {
				msg = jsonNode.get("desc").asText();
			}
			objectNode.put("code", code);
			objectNode.put("msg", msg);
			objectNode.set("ret", jsonNode.get(retKey));
			NeteaseImResponse<T> responseObj = JacksonUtil.decode(objectNode.toString(),
					JacksonUtil.getGenericType(NeteaseImResponse.class, javaType));
			return responseObj;
		} catch (IOException e) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "请求网易云信IM接口返回的json解析异常");
		}
	}

	public static String objectToQueryString(Object obj) {
		StringBuilder strBuilder = new StringBuilder();
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				String methodName = "get" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
				try {
					Method method = clazz.getMethod(methodName);
					Object fieldValue = method.invoke(obj);
					if (fieldValue != null) {
						strBuilder.append(field.getName() + "=" + method.invoke(obj) + "&");
					}
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
				}
			}
		}
		if (strBuilder.length() > 0) {
			strBuilder.deleteCharAt(strBuilder.length() - 1);
		}
		return strBuilder.toString();
	}

	public static void testMain(String[] args) {
		// testCreateUser();
		// testRefreshToken();
		// testCreateChatroom();
		// testRequestAddr();
	}

	public static void testCreateUser() {
		NeteaseUserCreateParam createParam = new NeteaseUserCreateParam();
		createParam.setAccid("13928952254");
		System.out.println(JacksonUtil.encode(doAction(NeteaseImConfig.CreateUrl, createParam,
				NeteaseImConfig.CreateRetKey, NeteaseUserCreateRet.class)));
	}

	public static void testRefreshToken() {
		NeteaseRefreshTokenParam createParam = new NeteaseRefreshTokenParam();
		createParam.setAccid("13928952254");
		System.out.println(JacksonUtil.encode(doAction(NeteaseImConfig.RefreshTokenUrl, createParam,
				NeteaseImConfig.RefreshTokenRetKey, NeteaseRefreshTokenRet.class)));
	}

	public static void testCreateChatroom() {
		NeteaseChatroomCreateParam createParam = new NeteaseChatroomCreateParam();
		createParam.setCreator("1");
		createParam.setName("测试房间");
		System.out.println(JacksonUtil.encode(doAction(NeteaseImConfig.ChatroomCreateUrl, createParam,
				NeteaseImConfig.ChatroomCreateRetKey, NeteaseChatroomCreateRet.class)));
	}

	public static void testRequestAddr() {
		NeteaseChatroomRequestAddrParam addressParam = new NeteaseChatroomRequestAddrParam();
		addressParam.setAccid("13928952254");
		addressParam.setRoomid(23070177L);
		addressParam.setClienttype(1);
		System.out.println(JacksonUtil.encode(doAction(NeteaseImConfig.RequestAddrUrl, addressParam,
				NeteaseImConfig.RequestAddrRetKey, JacksonUtil.getGenericType(List.class, String.class))));
	}

}
