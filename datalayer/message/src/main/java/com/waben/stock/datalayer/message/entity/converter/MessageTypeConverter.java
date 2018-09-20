package com.waben.stock.datalayer.message.entity.converter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.MessageType;

public class MessageTypeConverter implements AttributeConverter<MessageType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(MessageType attribute) {
		return Integer.valueOf(attribute.getIndex());
	}

	@Override
	public MessageType convertToEntityAttribute(Integer dbData) {
		return MessageType.getByType(String.valueOf(dbData));
	}

}
