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

import javax.annotation.Resource;

import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.services.PriceService;
import com.serpics.i18n.data.model.Currency;
import com.serpics.stereotype.StoreStrategy;

@StoreStrategy("priceStrategy")
public class PriceStrategyImpl extends AbstractStrategy implements PriceStrategy {

	@Resource(name="priceService")
	PriceService priceService;
	
	@Override
	public Double resolveProductPrice(AbstractProduct product, Currency currency) throws PriceNotFoundException{
		Price price = priceService.findProductPrice(product , currency);
		return price.getCurrentPrice() != null ? price.getCurrentPrice() : new Double(0);
	}

	@Override
	public Double resolveProductCost(AbstractProduct product, Currency currency) throws PriceNotFoundException{
		Price price = priceService.findProductPrice(product , currency);
		return price.getProductCost() != null ? price.getProductCost() : new Double(0);
	}

}
