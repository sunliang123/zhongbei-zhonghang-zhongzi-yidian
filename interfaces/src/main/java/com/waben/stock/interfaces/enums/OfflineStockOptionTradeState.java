package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 线下期权交易状态
 * 
 * @author luomengan
 *
 */
public enum OfflineStockOptionTradeState implements CommonalityEnum {
	
	TURNOVER("1", "持仓中"),

	APPLYRIGHT("2", "申请行权"),

	INSETTLEMENT("3", "结算中"),

	SETTLEMENTED("4", "已结算"),

	INQUIRY("5","已询价"),

	PURCHASE("6","已申购");

	private String index;

	private String state;

	private OfflineStockOptionTradeState(String index, String state) {
		this.index = index;
		this.state = state;
	}

	private static Map<String, OfflineStockOptionTradeState> indexMap = new HashMap<String, OfflineStockOptionTradeState>();

	static {
		for (OfflineStockOptionTradeState _enum : OfflineStockOptionTradeState.values()) {
			indexMap.put(_enum.getIndex(), _enum);
		}
	}

	public static OfflineStockOptionTradeState getByIndex(String index) {
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
