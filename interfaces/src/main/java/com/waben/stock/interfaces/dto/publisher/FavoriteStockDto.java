package com.waben.stock.interfaces.dto.publisher;

import java.util.Date;

public class FavoriteStockDto {

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
	 * 指数代码
	 */
	private String exponentCode;
	/**
	 * 收藏时间
	 */
	private Date favoriteTime;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 策略发布人ID
	 */
	private Long publisherId;

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

	public Date getFavoriteTime() {
		return favoriteTime;
	}

	public void setFavoriteTime(Date favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getExponentCode() {
		return exponentCode;
	}

	public void setExponentCode(String exponentCode) {
		this.exponentCode = exponentCode;
	}

}
