/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.vaadin.ui.converters;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.base.MultiValueField;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Field;

public class AttributeTypeValueConverter implements Converter<String, MultiValueField>{

	Field<?> currentField ;
	
	public AttributeTypeValueConverter() {
		super();
		this.currentField = null;
	}
	
	public AttributeTypeValueConverter(Field<?> currentField) {
		super();
		this.currentField = currentField;
	}

	@Override
	public MultiValueField convertToModel(String value,
			Class<? extends MultiValueField> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
	
		MultiValueField field = null ;
		if (this.currentField != null)
			field = (MultiValueField) ((AbstractField)currentField).getPropertyDataSource().getValue();
	
		if(field == null){	
			try {
				field = targetType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
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
