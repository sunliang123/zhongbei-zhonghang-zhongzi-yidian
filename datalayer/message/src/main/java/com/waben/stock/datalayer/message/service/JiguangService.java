package com.waben.stock.datalayer.message.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送
 * 
 * @author luomengan
 *
 */
@Service
public class JiguangService {

	private static final String APP_KEY = "50a90087ee30438b218105b8";

	private static final String MASTER_SECRET = "93822312b1afc8fe1fab3f59";

	@Value("${spring.profiles.active}")
	private String activeProfile;

	private boolean isProd = true;

	Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		if ("prod".equals(activeProfile)) {
			isProd = true;
		} else {
			isProd = false;
		}
	}

	private PushPayload buildPushObjectForSingle(String registrationId, String title, String alert,
			Map<String, String> extras) {
		extras.put("sound", "default");
		Builder builder = PushPayload.newBuilder();
		builder.setPlatform(Platform.all());
		builder.setAudience(
				Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationId)).build());
		builder.setNotification(Notification.newBuilder()
				.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).addExtras(extras).build())
				.addPlatformNotification(
						AndroidNotification.newBuilder().setTitle(title).setAlert(alert).addExtras(extras).build())
				.build());
		// ture为生产环境，false为开发环境
		builder.setOptions(Options.newBuilder().setApnsProduction(isProd).build());
		return builder.build();
	}

	private PushPayload buildPushObjectForMultiple(String[] registrationIdArr, String title, String alert,
			Map<String, String> extras) {
		extras.put("sound", "default");
		Builder builder = PushPayload.newBuilder();
		builder.setPlatform(Platform.all());
		builder.setAudience(
				Audience.newBuilder().addAudienceTarget(AudienceTarget.registrationId(registrationIdArr)).build());
		builder.setNotification(Notification.newBuilder()
				.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).addExtras(extras).build())
				.addPlatformNotification(
						AndroidNotification.newBuilder().setTitle(title).setAlert(alert).addExtras(extras).build())
				.build());
		// ture为生产环境，false为开发环境
		builder.setOptions(Options.newBuilder().setApnsProduction(isProd).build());
		return builder.build();
	}

	private PushPayload buildPushObjectForAllDevice(String title, String alert, Map<String, String> extras) {
		extras.put("sound", "default");
		Builder builder = PushPayload.newBuilder();
		builder.setPlatform(Platform.all());
		builder.setAudience(Audience.newBuilder().setAll(true).build());
		builder.setNotification(Notification.ios(alert, extras));
		builder.setNotification(Notification.android(alert, title, extras));
		// ture为生产环境，false为开发环境
		builder.setOptions(Options.newBuilder().setApnsProduction(isProd).build());
		return builder.build();
	}

	/**
	 * 推送通知
	 */
	private void pushNotification(PushPayload payload, String appKey, String masterSecret) {
		JPushClient jpushClient = new JPushClient(masterSecret != null ? masterSecret : MASTER_SECRET, appKey != null ? appKey : APP_KEY, null, ClientConfig.getInstance());
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION,
					"Jiguang push connection error, should retry later");
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION,
					"Jiguang push should review the error, and fix the request");
		}
	}

	public void pushAllDevice(String title, String alert, Map<String, String> extras, String appKey, String masterSecret) {
		pushNotification(buildPushObjectForAllDevice(title, alert, extras), appKey, masterSecret);
	}

	public void pushSingleDevice(String registrationId, String title, String alert, Map<String, String> extras, String appKey, String masterSecret) {
		pushNotification(buildPushObjectForSingle(registrationId, title, alert, extras), appKey, masterSecret);
	}

	public void pushMultipleDevice(String[] registrationIdArr, String title, String alert, Map<String, String> extras, String appKey, String masterSecret) {
		pushNotification(buildPushObjectForMultiple(registrationIdArr, title, alert, extras), appKey, masterSecret);
	}

}
