package com.waben.stock.datalayer.organization.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;

/**
 * 机构账户流水类型 转换器
 * 
 * @author luomengan
 *
 */
public class OrganizationAccountFlowTypeConverter implements AttributeConverter<OrganizationAccountFlowType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(OrganizationAccountFlowType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public OrganizationAccountFlowType convertToEntityAttribute(Integer dbData) {
		return OrganizationAccountFlowType.getByIndex(String.valueOf(dbData));
	}
}
