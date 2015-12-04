package com.serpics.vaadin.ui.converters;

import java.util.Date;
import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.base.AttributeType;
import com.serpics.base.MultiValueField;
import com.vaadin.data.util.converter.Converter;

public class AttributeTypeDateConverter implements Converter<Date, MultiValueField>{

	@Override
	public MultiValueField convertToModel(Date value,
			Class<? extends MultiValueField> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
	
		MultiValueField field;
			try {
				field = targetType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			field.setAttributeType(AttributeType.DATE);
	        field.setDateValue(value);
	        return field;
	}

	@Override
	public Date convertToPresentation(MultiValueField value,
			Class<? extends Date> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (StringUtils.isEmpty(value))
			return null;
		
		return  value.getDateValue();
	}

	@Override
	public Class<MultiValueField> getModelType() {
	
		return MultiValueField.class;
	}

	@Override
	public Class<Date> getPresentationType() {
		return Date.class;
	}

}
