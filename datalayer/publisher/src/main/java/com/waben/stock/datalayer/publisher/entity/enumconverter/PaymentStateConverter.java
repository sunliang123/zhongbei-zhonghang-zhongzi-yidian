package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.PaymentState;

/**
 * 支付状态 转换器
 * 
 * @author luomengan
 *
 */
public class PaymentStateConverter implements AttributeConverter<PaymentState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(PaymentState attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public PaymentState convertToEntityAttribute(Integer dbData) {
		return PaymentState.getByIndex(String.valueOf(dbData));
	}
}
