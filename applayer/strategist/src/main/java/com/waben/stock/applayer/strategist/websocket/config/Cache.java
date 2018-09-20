package com.waben.stock.applayer.strategist.websocket.config;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	/**
	 * key为股票代码，value为订阅的用户数
	 */
	public static Map<String, Integer> timeLineCache = new HashMap<>();
	/**
	 * key为sessionId，value为股票代码
	 */
	public static Map<String, String> timeLineSessionIdCache = new HashMap<>();

	public synchronized static void subscribeTimeLine(String stockCode) {
		Integer size = timeLineCache.get(stockCode);
		if (size == null) {
			timeLineCache.put(stockCode, 1);
		} else {
			timeLineCache.put(stockCode, size + 1);
		}
	}

	public synchronized static void unsubscribeTimeLine(String stockCode) {
		Integer size = timeLineCache.get(stockCode);
		if (size != null) {
			if (size - 1 <= 0) {
				timeLineCache.remove(stockCode);
			} else {
				timeLineCache.put(stockCode, size - 1);
			}
		}
	}

}
