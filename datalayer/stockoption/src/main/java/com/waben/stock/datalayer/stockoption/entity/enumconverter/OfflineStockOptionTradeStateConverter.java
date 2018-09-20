package com.waben.stock.datalayer.stockoption.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;

/**
 * 线下期权交易状态 转换器
 * 
 * @author luomengan
 *
 */
public class OfflineStockOptionTradeStateConverter implements AttributeConverter<OfflineStockOptionTradeState, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(OfflineStockOptionTradeState attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public OfflineStockOptionTradeState convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return OfflineStockOptionTradeState.getByIndex(String.valueOf(dbData));
	}
}
