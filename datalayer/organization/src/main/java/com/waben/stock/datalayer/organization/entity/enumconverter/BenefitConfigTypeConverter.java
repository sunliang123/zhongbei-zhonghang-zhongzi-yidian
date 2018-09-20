package com.waben.stock.datalayer.organization.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.BenefitConfigType;

/**
 * 分成配置类型 转换器
 * 
 * @author luomengan
 *
 */
public class BenefitConfigTypeConverter implements AttributeConverter<BenefitConfigType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(BenefitConfigType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public BenefitConfigType convertToEntityAttribute(Integer dbData) {
		return BenefitConfigType.getByIndex(String.valueOf(dbData));
	}
}
