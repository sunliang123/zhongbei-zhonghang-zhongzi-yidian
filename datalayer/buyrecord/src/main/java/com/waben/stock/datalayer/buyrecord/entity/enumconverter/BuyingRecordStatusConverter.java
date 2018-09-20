package com.waben.stock.datalayer.buyrecord.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.BuyRecordState;

/**
 * 点买记录状态 转换器
 * 
 * @author luomengan
 *
 */
public class BuyingRecordStatusConverter implements AttributeConverter<BuyRecordState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(BuyRecordState attribute) {
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public BuyRecordState convertToEntityAttribute(Integer dbData) {
		return BuyRecordState.getByIndex(String.valueOf(dbData));
	}
}
