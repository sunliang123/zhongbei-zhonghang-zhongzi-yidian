package com.waben.stock.applayer.strategist.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.enums.SmsType;
import com.waben.stock.interfaces.exception.ServiceException;

/**
 * 发送成功的短信 缓存
 * 
 * @author luomengan
 *
 */
@Component("strategistSmsCache")
public class SmsCache {

	/**
	 * 短信有效期，15分钟，单位秒
	 */
	public static final int effectiveDuration = 15 * 60;
	/**
	 * 短信发送最短间隔，1分钟，即1分钟内不能重复发送，单位毫秒
	 */
	public static final long sendingInterval = 1 * 60 * 1000;
	/**
	 * redis缓存
	 */
	@Autowired
	private RedisCache redisCache;

	public static String getCacheKey(SmsType smsType, String phone, String cacheName) {
		return "sms:" + smsType.name() + "_" + phone + "_" + cacheName;
	}

	public static String getLastTimeCacheKey(SmsType smsType, String phone) {
		return "sms:" + smsType.name() + "_lasttime_" + phone;
	}

	/**
	 * 缓存验证码信息
	 */
	public void cache(SmsType smsType, String phone, Map<String, String> params, String[] cacheNames) {
		if (cacheNames != null && cacheNames.length > 0) {
			for (int i = 0; i < cacheNames.length; i++) {
				redisCache.set(getCacheKey(smsType, phone, cacheNames[i]), params.get(cacheNames[i]),
						effectiveDuration);
			}
		}
		redisCache.set(getLastTimeCacheKey(smsType, phone), String.valueOf(System.currentTimeMillis()));
	}

	/**
	 * 检查发送条件
	 */
	public void checkSendCondition(SmsType smsType, String phone) {
		String lastTime = redisCache.get(getLastTimeCacheKey(smsType, phone));
		if (lastTime != null && !"".equals(lastTime)
				&& (Long.parseLong(lastTime) + sendingInterval) > new Date().getTime()) {
			throw new ServiceException(ExceptionConstant.SENDMESSAGE_INTERVAL_TOOSHORT_EXCEPTION);
		}
	}

	/**
	 * 匹配验证码
	 */
	public void matchVerificationCode(SmsType smsType, String phone, String cacheName, String cacheValue) {
		String oldCache = redisCache.get(getCacheKey(smsType, phone, cacheName));
		if (!(oldCache != null && oldCache.equals(cacheValue))) {
			throw new ServiceException(ExceptionConstant.VERIFICATIONCODE_INVALID_EXCEPTION);
		}
	}

}
