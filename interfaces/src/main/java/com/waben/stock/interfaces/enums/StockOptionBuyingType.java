package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 期权买入方式
 * 
 * @author luomengan
 *
 */
public enum StockOptionBuyingType implements CommonalityEnum {

	MARKETBUYING("1", "市价买入"),
	
	AVGBUYING("2", "均价买入");

	private String index;

	private String state;

	private StockOptionBuyingType(String index, String state) {
		this.index = index;
		this.state = state;
	}

	private static Map<String, StockOptionBuyingType> indexMap = new HashMap<String, StockOptionBuyingType>();

	static {
		for (StockOptionBuyingType _enum : StockOptionBuyingType.values()) {
			indexMap.put(_enum.getIndex(), _enum);
		}
	}

	public static StockOptionBuyingType getByIndex(String index) {
		return indexMap.get(index);
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
