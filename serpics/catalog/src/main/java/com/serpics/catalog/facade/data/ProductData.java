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
package com.serpics.catalog.facade.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"sort","numberOfElements","first","last","size","number","content","medias","buyable","dowloadable","totalPages","totalElements" })
public class ProductData  extends AbstractProductData{
	
	protected BrandData brand;
	protected boolean published;
	
	protected Set<CategoryData> categories;
	
	protected List<ProductVariantData> variants;

	protected String productType;
	
	protected Map<String , List<AttributeValueData>> variantValues;
	
	public BrandData getBrand() {
		return brand;
	}

	public void setBrand(BrandData brand) {
		this.brand = brand;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Set<CategoryData> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryData> categories) {
		this.categories = categories;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<ProductVariantData> getVariants() {
		return variants;
	}

	public void setVariants(List<ProductVariantData> variants) {
		this.variants = variants;
	}

	public Map<String, List<AttributeValueData>> getVariantValues() {
		return variantValues;
	}

	public void setVariantValues(Map<String, List<AttributeValueData>> variantValues) {
		this.variantValues = variantValues;
	}

}
