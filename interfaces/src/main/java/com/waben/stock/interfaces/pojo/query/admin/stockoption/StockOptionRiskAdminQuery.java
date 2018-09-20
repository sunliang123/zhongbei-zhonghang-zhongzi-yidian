package com.waben.stock.interfaces.pojo.query.admin.stockoption;

import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;

public class StockOptionRiskAdminQuery extends PageAndSortQuery {

	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 期权周期
	 */
	private Long cycleId;
	/**
	 * 股票状态
	 * <p>
	 * 是否停牌，非开始闭市状态
	 * </p>
	 */
	private Boolean status;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
