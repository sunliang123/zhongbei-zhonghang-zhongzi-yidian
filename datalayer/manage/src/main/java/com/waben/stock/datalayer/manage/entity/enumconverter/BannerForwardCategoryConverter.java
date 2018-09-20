package com.waben.stock.datalayer.manage.entity.enumconverter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.BannerForwardCategory;

/**
 * 跳转类别 转换器
 * 
 * @author luomengan
 *
 */
public class BannerForwardCategoryConverter implements AttributeConverter<BannerForwardCategory, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(BannerForwardCategory attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.parseInt(attribute.getIndex());
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public BannerForwardCategory convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return BannerForwardCategory.getByIndex(String.valueOf(dbData));
	}
}
