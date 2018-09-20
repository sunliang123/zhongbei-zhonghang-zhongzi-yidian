package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.CapitalFlowType;

/**
 * 资金流水类型 转换器
 * 
 * @author luomengan
 *
 */
public class CapitalFlowTypeConverter implements AttributeConverter<CapitalFlowType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(CapitalFlowType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public CapitalFlowType convertToEntityAttribute(Integer dbData) {
		return CapitalFlowType.getByIndex(String.valueOf(dbData));
	}
}
