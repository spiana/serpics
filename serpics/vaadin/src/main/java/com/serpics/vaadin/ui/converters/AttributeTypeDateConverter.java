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

import java.util.Date;
import java.util.Locale;

import org.springframework.util.StringUtils;

import com.serpics.core.data.model.MultiValueField;
import com.serpics.core.datatype.AttributeType;
import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.Field;

public class AttributeTypeDateConverter implements Converter<Date, MultiValueField>{

	Field<?> currentField ;
	
	public AttributeTypeDateConverter() {
		super();
		currentField = null;
	}

	public AttributeTypeDateConverter(Field<?> currentField) {
		super();
		this.currentField = currentField;
	}

	@Override
	public MultiValueField convertToModel(Date value,
			Class<? extends MultiValueField> targetType, Locale locale)
			throws ConversionException {
	
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
			field.setAttributeType(AttributeType.DATE);
	        field.setDateValue(value);
	        return field;
	}

	@Override
	public Date convertToPresentation(MultiValueField value,
			Class<? extends Date> targetType, Locale locale)
			throws ConversionException {
		
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
