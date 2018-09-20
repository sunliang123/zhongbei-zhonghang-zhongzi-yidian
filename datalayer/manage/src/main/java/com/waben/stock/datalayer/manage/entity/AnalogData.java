package com.waben.stock.datalayer.manage.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.waben.stock.datalayer.manage.entity.enumconverter.AnalogDataTypeConverter;
import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 
 * 模拟数据
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "analog_data")
public class AnalogData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Convert(converter = AnalogDataTypeConverter.class)
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
