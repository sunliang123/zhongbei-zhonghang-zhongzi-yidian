package com.waben.stock.datalayer.stockoption.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.StockOptionBuyingType;

/**
 * 买入方式 转换器
 * 
 * @author luomengan
 *
 */
public class StockOptionBuyingTypeConverter implements AttributeConverter<StockOptionBuyingType, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(StockOptionBuyingType attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public StockOptionBuyingType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return StockOptionBuyingType.getByIndex(String.valueOf(dbData));
	}
}
