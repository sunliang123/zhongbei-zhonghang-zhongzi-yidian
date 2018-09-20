package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户交易状态
 * 
 * @author luomengan
 *
 */
public enum StockOptionTradeState implements CommonalityEnum {

	WAITCONFIRMED("1", "待确认"),

	FAILURE("2", "申购失败"),

	TURNOVER("3", "持仓中"),

	APPLYRIGHT("4", "申请卖出"),

	INSETTLEMENT("5", "结算中"),

	SETTLEMENTED("6", "已结算"),

	AUTOEXPIRE("7", "自动到期");

	private String index;

	private String state;

	private StockOptionTradeState(String index, String state) {
		this.index = index;
		this.state = state;
	}

	private static Map<String, StockOptionTradeState> indexMap = new HashMap<String, StockOptionTradeState>();

	static {
		for (StockOptionTradeState _enum : StockOptionTradeState.values()) {
			indexMap.put(_enum.getIndex(), _enum);
		}
	}

	public static StockOptionTradeState getByIndex(String index) {
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
