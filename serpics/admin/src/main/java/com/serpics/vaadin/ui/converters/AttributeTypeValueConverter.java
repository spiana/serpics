package com.serpics.vaadin.ui.converters;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.base.MultiValueField;
import com.vaadin.data.util.converter.Converter;

public class AttributeTypeValueConverter implements Converter<String, MultiValueField>{

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

	        field.setStringValue(value);
	        return field;
	}

	@Override
	public String convertToPresentation(MultiValueField value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (StringUtils.isEmpty(value))
			return "";
		
		switch (value.getAttributeType()) {
		case STRING:
			return value.getStringValue() != null?value.getStringValue() :"";
		case INTEGER:
			return String.valueOf(value.getIntegerValue()!= null ? value.getIntegerValue():"");
		case DOUBLE:
			return String.valueOf(value.getDoubleValue() != null && locale != null? DecimalFormat.getInstance(locale).format(value.getDoubleValue()):"");
		case DATE:
			DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT, locale);
			return String.valueOf(value.getDateValue() != null ? df.format(value.getDateValue()) : "" );
		
		}		
		return null;
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
