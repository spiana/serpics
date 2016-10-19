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
package com.serpics.catalog.services;

import java.util.List;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.service.EntityService;

public interface PriceService extends EntityService<Price, Long> {
	
    
    public List<Price> findValidPricesforProduct(AbstractProduct product, Pricelist pricelist , Currency cuurency);
    public List<Price> findValidPricesforProduct(AbstractProduct product);
    public Price findProductPrice(AbstractProduct product , Pricelist priceList , Currency currency ) throws PriceNotFoundException;
    public Price findProductPrice(AbstractProduct product) throws PriceNotFoundException;
    public Price findProductPrice(AbstractProduct product, Currency currency) throws PriceNotFoundException;
    
    public AbstractProduct addPrice(AbstractProduct product , Price price , Pricelist priceList);
    public AbstractProduct addPrice(AbstractProduct product , Price price );
    
}
