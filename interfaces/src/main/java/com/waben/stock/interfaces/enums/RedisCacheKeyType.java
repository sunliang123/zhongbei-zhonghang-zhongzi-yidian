package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

public enum RedisCacheKeyType implements CommonalityEnum {

	AppToken("1", "token:app:%s"),
	
	AppRegistrationId("2", "jiguang:registrationId:%s"), 
	
	StockMarket("3", "stock:market:%s");

	private String index;

	private String key;

	private RedisCacheKeyType(String index, String key) {
		this.index = index;
		this.key = key;
	}

	private static Map<String, RedisCacheKeyType> indexMap = new HashMap<String, RedisCacheKeyType>();

	static {
		for (RedisCacheKeyType _enum : RedisCacheKeyType.values()) {
			indexMap.put(_enum.getIndex(), _enum);
		}
	}

	public static RedisCacheKeyType getByIndex(String index) {
		return indexMap.get(index);
	}

	/*********************** setter and getter **************************/

	public String getIndex() {
		return index;
	}

	public String getKey() {
		return key;
	}

}
