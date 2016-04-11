/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.catalog.facade;

import javax.annotation.Resource;

import com.serpics.catalog.data.model.VariantAttribute;
import com.serpics.catalog.facade.data.AttributeValueData;
import com.serpics.catalog.facade.data.VariantAttributeData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

/**
 * @author spiana
 *
 */
public class VariantAttributePopulator implements Populator<VariantAttribute, VariantAttributeData>{
	
	@Resource(name="attributeValueConverter")
	AbstractPopulatingConverter<VariantAttribute, AttributeValueData> attributeValueConverter;
	
	/* (non-Javadoc)
	 * @see com.serpics.core.facade.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(VariantAttribute source, VariantAttributeData target) {
		
		target.setName(source.getBaseAttribute().getName());
		AttributeValueData value= attributeValueConverter.convert(source);
		target.setValue(value);
		
	}

}
