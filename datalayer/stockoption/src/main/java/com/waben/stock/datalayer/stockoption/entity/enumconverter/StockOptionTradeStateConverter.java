package com.waben.stock.datalayer.stockoption.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.StockOptionTradeState;

/**
 * 交易状态 转换器
 * 
 * @author luomengan
 *
 */
public class StockOptionTradeStateConverter implements AttributeConverter<StockOptionTradeState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(StockOptionTradeState attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public StockOptionTradeState convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return StockOptionTradeState.getByIndex(String.valueOf(dbData));
	}
}
