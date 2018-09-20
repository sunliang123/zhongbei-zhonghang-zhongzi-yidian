package com.waben.stock.datalayer.publisher.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 收藏股票
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "favorite_stock")
public class FavoriteStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 股票名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 股票代码
	 */
	@Column(name = "code")
	private String code;
	/**
	 * 指数代码
	 */
	@Column(name = "exponent_code")
	private String exponentCode;
	/**
	 * 收藏时间
	 */
	@Column(name = "favorite_time")
	private Date favoriteTime;
	/**
	 * 排序
	 */
	@Column(name = "sort")
	private Integer sort;
	/**
	 * 策略发布人ID
	 */
	@Column(name = "publisher_id")
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
