package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.BindCardResourceType;

/**
 * 绑卡资源 类型 转换器
 * 
 * @author luomengan
 *
 */
public class BindCardResourceTypeConverter implements AttributeConverter<BindCardResourceType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(BindCardResourceType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public BindCardResourceType convertToEntityAttribute(Integer dbData) {
		return BindCardResourceType.getByIndex(String.valueOf(dbData));
	}
}
