package com.khayayphyu.entity.convertor;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;

@Component
public class BooleanConvertor implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return attribute != null && attribute ? Data.YES.name() : Data.NO.name();
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return dbData != null && dbData.equals(Data.YES.name());
	}

	public static enum Data {
		YES, NO;
	}

}