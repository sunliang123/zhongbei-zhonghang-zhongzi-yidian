package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.FrozenCapitalType;

/**
 * 冻结类型 转换器
 * 
 * @author luomengan
 *
 */
public class FrozenCapitalTypeConverter implements AttributeConverter<FrozenCapitalType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(FrozenCapitalType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public FrozenCapitalType convertToEntityAttribute(Integer dbData) {
		return FrozenCapitalType.getByIndex(String.valueOf(dbData));
	}

}
