package com.serpics.vaadin.ui.converters;

import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.base.AttributeType;
import com.serpics.base.MultiValueField;
import com.vaadin.data.util.converter.Converter;

public class AttributeTypeIntegerConverter implements Converter<String, MultiValueField>{

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
			field.setAttributeType(AttributeType.INTEGER);
			if (value != null)
				try{
					field.setIntegerValue(Integer.valueOf(value));
				}catch (NumberFormatException e) {
					new ConversionException("Could not convert values ["+value + "] to number ");
				}
	        return field;
	}

	@Override
	public String convertToPresentation(MultiValueField value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (StringUtils.isEmpty(value))
			return null;
		
		Integer _lvalue = value.getIntegerValue();
		
		return _lvalue != null ?String.valueOf(_lvalue) : "";
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
