package com.waben.stock.applayer.strategist.dto.analogdata;

public class ExperienceSta {

	/**
	 * 总参与人数
	 */
	private Integer totalJoin;
	/**
	 * 今日剩余体验名额
	 */
	private Integer todayLeftOver;

	public Integer getTotalJoin() {
		return totalJoin;
	}

	public void setTotalJoin(Integer totalJoin) {
		this.totalJoin = totalJoin;
	}

	public Integer getTodayLeftOver() {
		return todayLeftOver;
	}

	public void setTodayLeftOver(Integer todayLeftOver) {
		this.todayLeftOver = todayLeftOver;
	}

}
