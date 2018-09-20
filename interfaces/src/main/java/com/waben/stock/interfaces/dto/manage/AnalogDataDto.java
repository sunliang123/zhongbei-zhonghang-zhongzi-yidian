package com.waben.stock.interfaces.dto.manage;

import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 
 * 模拟数据
 * 
 * @author luomengan
 *
 */
public class AnalogDataDto {

	private Long id;
	/**
	 * 数据
	 */
	private String data;
	/**
	 * 排序
	 */
	private Integer sortNum;
	/**
	 * 模拟数据类型
	 */
	private AnalogDataType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public AnalogDataType getType() {
		return type;
	}

	public void setType(AnalogDataType type) {
		this.type = type;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
