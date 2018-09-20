package com.waben.stock.interfaces.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RequestParamBuilder {

	public static String build(TreeMap paramMap) {
		StringBuilder builder = new StringBuilder();
		Set<Map.Entry> entrySet = paramMap.entrySet();
		for (Map.Entry entry : entrySet) {
			builder.append(entry.getKey() + "=" + String.valueOf(entry.getValue()) + "&");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

}
