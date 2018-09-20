package com.waben.stock.interfaces.commonapi.stocktrade.bean;

import java.util.List;

public class TradeResponse<T> {

	private List<TradeResultElement<T>> result;

	public List<TradeResultElement<T>> getResult() {
		return result;
	}

	public void setResult(List<TradeResultElement<T>> result) {
		this.result = result;
	}

}
