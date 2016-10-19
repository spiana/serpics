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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder({"sort","numberOfElements","first","last","size","number","content","medias","buyable","dowloadable","totalPages","totalElements" })
public class ProductVariantData  extends AbstractProductData{
	
	private double sequence;
	
	private List<ProductVariantData> variants;
	
	private List<VariantAttributeData> attributes;

	public double getSequence() {
		return sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public List<VariantAttributeData> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<VariantAttributeData> attributes) {
		this.attributes = attributes;
	}

	public List<ProductVariantData> getVariants() {
		return variants;
	}

	public void setVariants(List<ProductVariantData> variants) {
		this.variants = variants;
	}
		
}
