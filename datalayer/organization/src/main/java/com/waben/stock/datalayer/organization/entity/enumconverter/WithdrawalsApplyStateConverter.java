package com.waben.stock.datalayer.organization.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.WithdrawalsApplyState;

/**
 * 资源类型 转换器
 * 
 * @author luomengan
 *
 */
public class WithdrawalsApplyStateConverter implements AttributeConverter<WithdrawalsApplyState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(WithdrawalsApplyState attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public WithdrawalsApplyState convertToEntityAttribute(Integer dbData) {
		return WithdrawalsApplyState.getByIndex(String.valueOf(dbData));
	}
}
