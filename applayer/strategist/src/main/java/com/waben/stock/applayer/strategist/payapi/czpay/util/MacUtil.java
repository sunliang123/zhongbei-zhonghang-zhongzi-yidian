package com.waben.stock.applayer.strategist.payapi.czpay.util;

import java.util.Arrays;
import java.util.Map;

import org.springframework.boot.json.GsonJsonParser;

public class MacUtil {
	
	public static String makeMac(String json, String macKey) {
		GsonJsonParser parser = new GsonJsonParser();
		// Map<String, String> contentData = (Map<String, String>) Tools.parserToMap(json);
		Map<String, Object> contentData = (Map<String, Object>) parser.parseMap(json);
		String macStr = "";
		Object[] key_arr = contentData.keySet().toArray();
		Arrays.sort(key_arr);
		for (Object key : key_arr) {
			Object value = contentData.get(key);
			if (value != null) {
				if (!key.equals("mac")) {
					macStr += value.toString();
				}
			}
		}
		String rMac = DESUtil.mac(macStr, macKey);
		return rMac;
	}
	
}
