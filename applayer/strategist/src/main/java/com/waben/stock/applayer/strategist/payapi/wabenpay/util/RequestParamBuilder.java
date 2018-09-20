package com.waben.stock.applayer.strategist.payapi.wabenpay.util;

import java.util.TreeMap;
import java.util.Map.Entry;

public class RequestParamBuilder {

	public static String build(TreeMap<String, String> paramMap) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : paramMap.entrySet()) {
			builder.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

}
