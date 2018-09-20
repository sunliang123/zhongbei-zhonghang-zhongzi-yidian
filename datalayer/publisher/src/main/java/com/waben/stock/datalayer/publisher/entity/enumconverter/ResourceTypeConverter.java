package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 资源类型 转换器
 * 
 * @author luomengan
 *
 */
public class ResourceTypeConverter implements AttributeConverter<ResourceType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(ResourceType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public ResourceType convertToEntityAttribute(Integer dbData) {
		return ResourceType.getByIndex(String.valueOf(dbData));
	}
}
