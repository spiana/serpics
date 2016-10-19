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

import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.facade.data.PriceData;
import com.serpics.core.facade.Populator;

public class PricePopulator implements Populator<Price, PriceData> {
	@Override
	public void populate(Price source, PriceData target) {
		if(source.getCurrentPrice() != null)
			target.setCurrentPrice(source.getCurrentPrice());
		if(source.getProductCost() != null)
			target.setProductCost(source.getProductCost());
		if(source.getProductPrice() != null)
			target.setProductPrice(source.getProductPrice());
		if(source.getPrecedence() != null)
			target.setPrecedence(source.getPrecedence().intValue());
	}
}
