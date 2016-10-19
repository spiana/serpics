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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.serpics.catalog.data.model.Brand;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.ProductVariant;
import com.serpics.catalog.facade.data.AttributeValueData;
import com.serpics.catalog.facade.data.BrandData;
import com.serpics.catalog.facade.data.CategoryData;
import com.serpics.catalog.facade.data.ProductData;
import com.serpics.catalog.facade.data.ProductVariantData;
import com.serpics.catalog.facade.data.VariantAttributeData;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.core.facade.Populator;

/**
 * @author spiana
 *
 */
public class ProductPopulator implements Populator<Product, ProductData>{
	private AbstractPopulatingConverter<Brand, BrandData> brandConverter;
	private AbstractPopulatingConverter<Category, CategoryData> categoryConverter;
	private AbstractPopulatingConverter<ProductVariant, ProductVariantData> variantConverter;

	/* (non-Javadoc)
	 * @see com.serpics.core.facade.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(Product source, ProductData target) {
		target.setPublished(source.isPublished());
		target.setProductType(source.getProductType().name());
		if(source.getBrand() != null)
			target.setBrand(brandConverter.convert(source.getBrand()));
		
		
		if(source.getCategories() != null) {
			Set<CategoryData> categories = new HashSet<CategoryData>();
			Set<CategoryProductRelation> categoriesrelation = source.getCategories();
			for (CategoryProductRelation categoryProductRelation : categoriesrelation) {
				Category category = categoryProductRelation.getParentCategory();
				categories.add(categoryConverter.convert(category));
			}
			target.setCategories(categories);
		}
		if(source.getVariants() != null){
			List<ProductVariantData> variants = new ArrayList<ProductVariantData>();
			for (ProductVariant variant : source.getVariants()) {
				ProductVariantData _v  = variantConverter.convert(variant);
				populateattributeValues(_v , target);
				variants.add(_v);
				
			}
			target.setVariants(variants);
		}
	}

	
	private void populateattributeValues (ProductVariantData variant , ProductData target){
		
		if (variant.getAttributes() == null)
			return; 
		
		for (VariantAttributeData attribute : variant.getAttributes() ) {
				String name = attribute.getName();
				if (target.getVariantValues() == null)
					target.setVariantValues( new HashMap<String , List<AttributeValueData>>());

				List<AttributeValueData> values  = target.getVariantValues().get(name);
				if (values == null){
					values = new ArrayList<AttributeValueData>();
					values.add(attribute.getValue());
				}
				target.getVariantValues().put(name, values);
		}
			
	}
	
	@Required
	public void setBrandConverter(AbstractPopulatingConverter<Brand, BrandData> brandConverter) {
		this.brandConverter = brandConverter;
	}
	@Required
	public void setCategoryConverter(AbstractPopulatingConverter<Category, CategoryData> categoryConverter) {
		this.categoryConverter = categoryConverter;
	}

	@Required
	public void setVariantConverter(
			AbstractPopulatingConverter<ProductVariant, ProductVariantData> variantConverter) {
		this.variantConverter = variantConverter;
	}
}
