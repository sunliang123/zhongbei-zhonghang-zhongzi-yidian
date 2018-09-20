package com.waben.stock.datalayer.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区域
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "area_info")
public class AreaInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 地区名称
	 */
	@Column(name = "area_name")
	private String areaName;
	/**
	 * 地区上级代码
	 */
	@Column(name = "parent_code")
	private String parentCode;
	/**
	 * 地区代码
	 */
	@Column(name = "area_code")
	private String areaCode;
	/**
	 * 地区级别
	 */
	@Column(name = "area_lev")
	private Integer areaLev;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getAreaLev() {
		return areaLev;
	}

	public void setAreaLev(Integer areaLev) {
		this.areaLev = areaLev;
	}

}
