package com.waben.stock.datalayer.buyrecord.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.WindControlType;

/**
 * 风控类型 转换器
 * 
 * @author luomengan
 *
 */
public class WindControlTypeConverter implements AttributeConverter<WindControlType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(WindControlType attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public WindControlType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return WindControlType.getByIndex(String.valueOf(dbData));
	}
}
