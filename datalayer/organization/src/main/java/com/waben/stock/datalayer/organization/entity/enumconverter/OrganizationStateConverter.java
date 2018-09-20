package com.waben.stock.datalayer.organization.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.OrganizationState;

/**
 * 机构状态 转换器
 * 
 * @author luomengan
 *
 */
public class OrganizationStateConverter implements AttributeConverter<OrganizationState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(OrganizationState attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public OrganizationState convertToEntityAttribute(Integer dbData) {
		return OrganizationState.getByIndex(String.valueOf(dbData));
	}
}
