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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.core.data.model.MultiValueField;
import com.serpics.core.datatype.AttributeType;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Field;

public class AttributeTypeDoubleConverter implements
		Converter<String, MultiValueField> {

	Field<?> currentField;

	public AttributeTypeDoubleConverter() {
		super();
		this.currentField = null;
	}
	
	public AttributeTypeDoubleConverter(Field<?> currentField) {
		super();
		this.currentField = currentField;
	}

	@Override
	public MultiValueField convertToModel(String value,
			Class<? extends MultiValueField> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {

		MultiValueField field = null;
		if (this.currentField != null)
			field = (MultiValueField) ((AbstractField) currentField)
					.getPropertyDataSource().getValue();

		if (field == null) {
			try {
				field = targetType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		field.setAttributeType(AttributeType.DOUBLE);
		if (value != null)
			try {
				field.setDoubleValue(DecimalFormat.getInstance(locale)
						.parse(value).doubleValue());
			} catch (ParseException e) {
				new ConversionException("Could not convert values [" + value
						+ "] to number ");
			}
		return field;
	}

	@Override
	public String convertToPresentation(MultiValueField value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {

		if (StringUtils.isEmpty(value))
			return null;

		Double _lvalue = value.getDoubleValue();

		return _lvalue != null && locale != null ? DecimalFormat.getInstance(
				locale).format(_lvalue) : "";
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
