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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.serpics.catalog.data.model.ProductVariant;
import com.serpics.catalog.data.model.VariantAttribute;
import com.serpics.catalog.facade.data.ProductVariantData;
import com.serpics.catalog.facade.data.VariantAttributeData;
import com.serpics.core.facade.AbstractConverter;
import com.serpics.core.facade.Populator;

/**
 * @author spiana
 *
 */
public class ProductVariantPopulator implements Populator<ProductVariant, ProductVariantData>{
	
	@Resource(name="variantAttributeConverter")
	AbstractConverter<VariantAttribute, VariantAttributeData> variantAttributeConverter;
	
	/* (non-Javadoc)
	 * @see com.serpics.core.facade.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(ProductVariant source, ProductVariantData target) {
		List<VariantAttributeData> attributes = new ArrayList<VariantAttributeData>();
		
		if (source.getAttributes() != null){
			for (VariantAttribute attribute : source.getAttributes()) {
				attributes.add(variantAttributeConverter.convert(attribute));
			}
		}
		target.setAttributes(attributes);
	}
	
}
