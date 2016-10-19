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
package com.serpics.commerce.strategies;

import java.util.Map;

import com.serpics.catalog.ProductNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.ProductVariant;


public interface ProductStrategy {

	public AbstractProduct resolveSKU(String sku) throws ProductNotFoundException;
	public ProductVariant resolveVariant(String sku , Map<String ,String > attributeMap );
}
