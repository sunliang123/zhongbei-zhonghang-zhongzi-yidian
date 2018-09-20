package com.waben.stock.datalayer.manage.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.waben.stock.datalayer.manage.entity.enumconverter.BannerForwardCategoryConverter;
import com.waben.stock.interfaces.enums.BannerForwardCategory;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc 轮播图跳转
 */
@Entity
@Table(name = "banner_forward")
public class BannerForward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 跳转代码
	 */
	@Column
	private String forward;
	/**
	 * 轮播图跳转描述
	 */
	@Column
	private String described;
	/**
	 * 跳转类别
	 */
	@Column
	@Convert(converter = BannerForwardCategoryConverter.class)
	private BannerForwardCategory category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described;
	}

	public BannerForwardCategory getCategory() {
		return category;
	}

	public void setCategory(BannerForwardCategory category) {
		this.category = category;
	}

}
