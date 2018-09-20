package com.waben.stock.datalayer.manage.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 模拟数据 转换器
 * 
 * @author luomengan
 *
 */
public class AnalogDataTypeConverter implements AttributeConverter<AnalogDataType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(AnalogDataType attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public AnalogDataType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return AnalogDataType.getByIndex(String.valueOf(dbData));
	}
}
