package com.waben.stock.datalayer.message.entity.converter;

import javax.persistence.AttributeConverter;

import com.waben.stock.interfaces.enums.ResourceType;

public class ResourceTypeConverter implements AttributeConverter<ResourceType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(ResourceType attribute) {
		if (attribute == null) {
			return null;
		}
		return Integer.valueOf(attribute.getIndex());
	}

	@Override
	public ResourceType convertToEntityAttribute(Integer dbData) {
		if (dbData == null) {
			return null;
		}
		return ResourceType.getByIndex(String.valueOf(dbData));
	}

}
