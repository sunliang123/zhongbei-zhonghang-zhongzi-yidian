package com.waben.stock.applayer.strategist.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 * stomp连接处理类
 */
// @Component
public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
		String sessionId = headerAccessor.getSessionId();
		if (headerAccessor.getCommand() != null) {
			MessageHeaders headers = headerAccessor.getMessageHeaders();
			switch (headerAccessor.getCommand()) {
			case SUBSCRIBE:
				subscribe(headers.get("simpDestination"), sessionId);
				break;
			case UNSUBSCRIBE:
				unsubscribe(headers.get("simpDestination"), sessionId);
				break;
			case DISCONNECT:
				String stockCode = Cache.timeLineSessionIdCache.get(sessionId);
				if (stockCode != null) {
					Cache.timeLineSessionIdCache.get(sessionId);
					Cache.unsubscribeTimeLine(stockCode);
					logger.info("stomp remove stockTimeLine : " + stockCode);
				}
				break;
			default:
				break;
			}
		}
	}

	private void subscribe(Object simpDestinationObj, String sessionId) {
		if (simpDestinationObj == null) {
			return;
		}
		String simpDestination = simpDestinationObj.toString();
		if (simpDestination.startsWith("/user/") && simpDestination.endsWith("/stockTimeLine")) {
			String stockCode = simpDestination.substring(6, simpDestination.indexOf("/stockTimeLine"));
			Cache.subscribeTimeLine(stockCode);
			Cache.timeLineSessionIdCache.put(sessionId, stockCode);
			logger.info("stomp add stockTimeLine : " + stockCode);
		}
	}

	private void unsubscribe(Object simpDestinationObj, String sessionId) {
		if (simpDestinationObj == null) {
			return;
		}
		String simpDestination = simpDestinationObj.toString();
		if (simpDestination.startsWith("/user/") && simpDestination.endsWith("/stockTimeLine")) {
			String stockCode = simpDestination.substring(6, simpDestination.indexOf("/stockTimeLine"));
			Cache.unsubscribeTimeLine(stockCode);
			Cache.timeLineSessionIdCache.remove(sessionId);
			logger.info("stomp remove stockTimeLine : " + stockCode);
		}
	}

}
