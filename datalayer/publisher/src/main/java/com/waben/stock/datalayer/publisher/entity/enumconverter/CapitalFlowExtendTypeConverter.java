package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.CapitalFlowExtendType;

/**
 * 资金流水扩展 类型 转换器
 * 
 * @author luomengan
 *
 */
public class CapitalFlowExtendTypeConverter implements AttributeConverter<CapitalFlowExtendType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(CapitalFlowExtendType attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public CapitalFlowExtendType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return CapitalFlowExtendType.getByIndex(String.valueOf(dbData));
	}
}
