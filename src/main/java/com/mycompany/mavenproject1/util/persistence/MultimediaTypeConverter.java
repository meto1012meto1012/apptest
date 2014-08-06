
package com.mycompany.mavenproject1.util.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class MultimediaTypeConverter implements AttributeConverter<MultimediaType, String> {
	@Override
	public String convertToDatabaseColumn(MultimediaType multimediaType) {
		return multimediaType.getValue();
	}

	@Override
	public MultimediaType convertToEntityAttribute(String value) {
		return MultimediaType.build(value);
	}
}
