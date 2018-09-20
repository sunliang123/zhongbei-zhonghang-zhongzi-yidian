package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.FrozenCapitalStatus;

/**
 * 冻结资金状态 转换器
 * 
 * @author luomengan
 *
 */
public class FrozenCapitalStatusConverter implements AttributeConverter<FrozenCapitalStatus, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(FrozenCapitalStatus attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public FrozenCapitalStatus convertToEntityAttribute(Integer dbData) {
		return FrozenCapitalStatus.getByIndex(String.valueOf(dbData));
	}
}
