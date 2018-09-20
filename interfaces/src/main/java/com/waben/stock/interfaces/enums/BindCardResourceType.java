package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 绑卡资源 类型
 * 
 * @author luomengan
 *
 */
public enum BindCardResourceType implements CommonalityEnum {

	PUBLISHER("1", "发布人"),

	ORGANIZATION("2", "机构");

	private String index;
	private String type;

	private BindCardResourceType(String index, String type) {
		this.index = index;
		this.type = type;
	}

	private static Map<String, BindCardResourceType> valueMap = new HashMap<String, BindCardResourceType>();

	static {
		for (BindCardResourceType _enum : BindCardResourceType.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static BindCardResourceType getByIndex(String index) {
		BindCardResourceType result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
