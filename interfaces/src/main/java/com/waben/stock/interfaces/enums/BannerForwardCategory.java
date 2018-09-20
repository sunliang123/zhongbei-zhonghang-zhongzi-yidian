package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * banner跳转类别
 * 
 * @author luomengan
 *
 */
public enum BannerForwardCategory implements CommonalityEnum {

	APP("1", "APP"),

	PC("2", "PC"), 
	
	APPSHELVES("3", "app上架使用");

	private String index;

	private String category;

	BannerForwardCategory(String index, String category) {
		this.index = index;
		this.category = category;
	}

	private static Map<String, BannerForwardCategory> map = new HashMap<String, BannerForwardCategory>();

	static {
		for (BannerForwardCategory _enum : BannerForwardCategory.values()) {
			map.put(_enum.getIndex(), _enum);
		}
	}

	public static BannerForwardCategory getByIndex(String index) {
		return map.get(index);
	}

	/*********************** setter and getter **************************/

	public String getIndex() {
		return index;
	}

	public String getCategory() {
		return category;
	}

}
