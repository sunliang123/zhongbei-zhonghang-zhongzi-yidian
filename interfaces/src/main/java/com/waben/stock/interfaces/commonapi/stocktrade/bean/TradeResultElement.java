package com.waben.stock.interfaces.commonapi.stocktrade.bean;

import java.util.List;

public class TradeResultElement<T> {
	/**
	 * 结果数据
	 * <p>
	 * data和msg只有一个会有值，另外一个为null
	 * </p>
	 */
	private List<T> data;
	/**
	 * 结果消息
	 * <p>
	 * data和msg只有一个会有值，另外一个为null
	 * </p>
	 */
	private TradeMessage msg;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public TradeMessage getMsg() {
		return msg;
	}

	public void setMsg(TradeMessage msg) {
		this.msg = msg;
	}

}
