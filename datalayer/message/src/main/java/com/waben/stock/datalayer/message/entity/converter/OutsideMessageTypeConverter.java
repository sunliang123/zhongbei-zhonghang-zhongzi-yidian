package com.waben.stock.datalayer.message.entity.converter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.OutsideMessageType;

/**
 * @author Created by yuyidi on 2018/1/3.
 * @desc
 */
public class OutsideMessageTypeConverter implements AttributeConverter<OutsideMessageType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(OutsideMessageType attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.valueOf(attribute.getIndex());
	}

	@Override
	public OutsideMessageType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return OutsideMessageType.getByType(String.valueOf(dbData));
	}

}
