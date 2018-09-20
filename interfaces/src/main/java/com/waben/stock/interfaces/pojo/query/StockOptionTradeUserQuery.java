package com.waben.stock.interfaces.pojo.query;

import java.util.Date;

import com.waben.stock.interfaces.enums.StockOptionTradeState;

public class StockOptionTradeUserQuery extends PageAndSortQuery {

	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 交易状态数组
	 */
	private StockOptionTradeState[] states;
	/**
	 * 查询收益了的交易
	 */
	private boolean onlyProfit;
	/**
	 * 申购时间_开始
	 */
	private Date startApplyTime;
	/**
	 * 申购时间_结束
	 */
	private Date endApplyTime;

	public StockOptionTradeUserQuery() {

	}

	public StockOptionTradeUserQuery(int page, int size, Long publisherId, StockOptionTradeState[] states) {
		super.setPage(page);
		super.setSize(size);
		this.publisherId = publisherId;
		this.states = states;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public StockOptionTradeState[] getStates() {
		return states;
	}

	public void setStates(StockOptionTradeState[] states) {
		this.states = states;
	}

	public boolean isOnlyProfit() {
		return onlyProfit;
	}

	public void setOnlyProfit(boolean onlyProfit) {
		this.onlyProfit = onlyProfit;
	}

	public Date getStartApplyTime() {
		return startApplyTime;
	}

	public void setStartApplyTime(Date startApplyTime) {
		this.startApplyTime = startApplyTime;
	}

	public Date getEndApplyTime() {
		return endApplyTime;
	}

	public void setEndApplyTime(Date endApplyTime) {
		this.endApplyTime = endApplyTime;
	}

}
