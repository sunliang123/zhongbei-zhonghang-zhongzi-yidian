package com.waben.stock.interfaces.dto.manage;

/**
 * 区域
 * 
 * @author luomengan
 *
 */
public class AreaInfoDto {

	private Long id;
	/**
	 * 地区名称
	 */
	private String areaName;
	/**
	 * 地区上级代码
	 */
	private String parentCode;
	/**
	 * 地区代码
	 */
	private String areaCode;
	/**
	 * 地区级别
	 */
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
