package com.waben.stock.interfaces.dto.stockcontent;

import java.util.Date;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
public class StockRecommendDto {

	/**
	 * 股票ID
	 */
	private Long id;
	/**
	 * 股票名称
	 */
	private String name;
	/**
	 * 股票代码
	 */
	private String code;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 推荐时间
	 */
	private Date recommendTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

}
