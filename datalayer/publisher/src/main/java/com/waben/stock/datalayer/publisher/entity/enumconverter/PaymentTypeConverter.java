package com.waben.stock.datalayer.publisher.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.PaymentType;

/**
 * 支付状态 转换器
 * 
 * @author luomengan
 *
 */
public class PaymentTypeConverter implements AttributeConverter<PaymentType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(PaymentType attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public PaymentType convertToEntityAttribute(Integer dbData) {
		return PaymentType.getByIndex(String.valueOf(dbData));
	}
}
