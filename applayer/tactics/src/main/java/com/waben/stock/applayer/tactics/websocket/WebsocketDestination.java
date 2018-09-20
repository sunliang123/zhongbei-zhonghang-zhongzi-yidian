package com.waben.stock.applayer.tactics.websocket;

public enum WebsocketDestination {

	/**
	 * 股票大盘指数
	 */
	StockMarketIndex(1, "/topic/stockMarketIndex"),

	/**
	 * 股票分时数据
	 */
	StockTimeLine(2, "/stockTimeLine");

	private int index;

	private String destination;

	private WebsocketDestination(int index, String destination) {
		this.index = index;
		this.destination = destination;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
