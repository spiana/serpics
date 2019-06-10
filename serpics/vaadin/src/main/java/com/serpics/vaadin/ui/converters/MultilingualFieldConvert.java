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

import com.serpics.i18n.data.model.MultilingualField;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Field;
public class MultilingualFieldConvert implements Converter<String, MultilingualField> {
    private static final long serialVersionUID = 1L;
  
    Field<?> textField;
    
    public MultilingualFieldConvert() {
  		super();
  	
  	}
    
    public MultilingualFieldConvert(Field<?> field) {
		super();
		this.textField = field;
	}

	@Override
    public MultilingualField convertToModel(final String value, final Class<? extends MultilingualField> targetType,
            final java.util.Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		MultilingualField field = null;
		 
    	 if (this.textField != null)
    		field = (MultilingualField) this.textField.getPropertyDataSource().getValue();
        
         if (field == null){ 
			try {
				field = targetType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

        field.addText(locale.getLanguage(), (String) value);
        return field;
    }

    @Override
    public String convertToPresentation(final MultilingualField value, final Class<? extends String> targetType,
            final java.util.Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {

        if (value != null && locale != null) {
            return value.getText(locale);
        }

        return "";
    }

 
    @Override
    public Class<MultilingualField> getModelType() {
        return MultilingualField.class;
    }

    @Override
    public Class<String> getPresentationType() {

        return String.class;
    }

}
