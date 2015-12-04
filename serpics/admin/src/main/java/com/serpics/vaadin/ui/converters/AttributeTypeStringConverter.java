package com.serpics.vaadin.ui.converters;

import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.base.AttributeType;
import com.serpics.base.MultiValueField;
import com.vaadin.data.util.converter.Converter;

public class AttributeTypeStringConverter implements Converter<String, MultiValueField>{

	@Override
	public MultiValueField convertToModel(String value,
			Class<? extends MultiValueField> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
	
		MultiValueField field;
			try {
				field = targetType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			field.setAttributeType(AttributeType.STRING);
	        field.setStringValue(value);
	        return field;
	}

	@Override
	public String convertToPresentation(MultiValueField value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (StringUtils.isEmpty(value))
			return null;
		
		return value.getStringValue();
	}

	@Override
	public Class<MultiValueField> getModelType() {
	
		return MultiValueField.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

}
