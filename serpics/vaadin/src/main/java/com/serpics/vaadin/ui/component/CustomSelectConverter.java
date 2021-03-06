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
package com.serpics.vaadin.ui.component;

import java.util.Locale;

import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.ui.AbstractSelect;

public class CustomSelectConverter extends SingleSelectConverter{

	private AbstractSelect select;
	public CustomSelectConverter(AbstractSelect select) {
		super(select);
		this.select = select;
	}

	@Override
	public Object convertToPresentation(Object value, Class targetType,
			Locale locale) throws ConversionException {
//			if (value.toString().contains("."))
//				((EntityContainer)this.select.getContainerDataSource()).addNestedContainerProperty(value.toString());
		
		return super.convertToPresentation(value, targetType, locale);
	}
}
