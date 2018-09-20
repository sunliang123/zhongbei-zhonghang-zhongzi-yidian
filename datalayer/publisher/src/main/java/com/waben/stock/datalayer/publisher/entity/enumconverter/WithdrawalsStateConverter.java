package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.WithdrawalsState;

/**
 * 提现状态 转换器
 * 
 * @author luomengan
 *
 */
public class WithdrawalsStateConverter implements AttributeConverter<WithdrawalsState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(WithdrawalsState attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public WithdrawalsState convertToEntityAttribute(Integer dbData) {
		return WithdrawalsState.getByIndex(String.valueOf(dbData));
	}
}
