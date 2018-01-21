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
package com.serpics.catalog.facade;

import java.text.DateFormat;
import java.text.NumberFormat;

import javax.annotation.Resource;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.catalog.data.model.VariantAttribute;
import com.serpics.catalog.facade.data.AttributeValueData;
import com.serpics.core.data.model.Locale;
import com.serpics.core.datatype.AttributeType;
import com.serpics.core.facade.Populator;

/**
 * @author spiana
 *
 */
public class AttributeValuePopulator implements Populator<VariantAttribute, AttributeValueData>{
	
	@Resource
	CommerceEngine engine;

	/* (non-Javadoc)
	 * @see com.serpics.core.facade.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(VariantAttribute source, AttributeValueData target) {
		
		Locale l = engine.getCurrentContext().getLocale();
		
		AttributeType type = source.getBaseAttribute().getAttributeType();
		
		switch (type) {

		case DATE:
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new java.util.Locale(l.getLanguage()));
			break;
		case STRING:
			target.setFormattedValue(source.getValue().getStringValue());
			if (source.getLocalize() != null)
				target.setLocalization(source.getLocalize().getText(new java.util.Locale(l.getLanguage())));
			break;
		case DOUBLE:
			NumberFormat dnf = NumberFormat.getIntegerInstance(new java.util.Locale(l.getLanguage()));
			if (source.getValue().getDoubleValue() != null)
				target.setFormattedValue(dnf.format(source.getValue().getDoubleValue()));
			break;
		case INTEGER:
			NumberFormat inf = NumberFormat.getIntegerInstance(new java.util.Locale(l.getLanguage()));
			if (source.getValue().getIntegerValue() != null)
				target.setFormattedValue(inf.format(source.getValue().getIntegerValue()));
			
			if (source.getLocalize() != null)
				target.setLocalization(source.getLocalize().getText(new java.util.Locale(l.getLanguage())));
			
			break;
		default:
			break;
		}
		
	}

}
