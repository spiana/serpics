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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.commerce.service.AbstractCommerceEntityService;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.AbstractProductRepository;
import com.serpics.catalog.data.repositories.PriceRepository;
import com.serpics.core.data.Repository;
import com.serpics.i18n.data.model.Currency;

@Service("priceService")
@Scope("store")
@Transactional(readOnly = true)
public class PriceServiceImpl extends AbstractCommerceEntityService<Price, Long> implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Resource
    PriceListService priceListService;
    
    @Autowired
    AbstractProductRepository productRepository;

    @Override
    public Price create(final Price entity) {
        if (entity.getCurrency() == null) {
            final Currency currency = ((Store) getCurrentContext().getStoreRealm()).getCurrency();
            entity.setCurrency(currency);
        }
        if (entity.getPricelist() == null) {
            entity.setPricelist(priceListService.getCurrentPriceList());
        }
        return super.create(entity);
    }

    @Override
    public Repository<Price, Long> getEntityRepository() {
        return priceRepository;
    }
  
    @Override
    public List<Price> findValidPricesforProduct(final AbstractProduct product, final Pricelist pricelist , Currency currency) {
        return priceRepository.findValidPricesForProduct(product, pricelist, currency);
    }
    @Override
    public List<Price> findValidPricesforProduct(final AbstractProduct product) {
        final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
        final Currency currency = (Currency) getCurrentContext().getCurrency();
        return findValidPricesforProduct(product, defaultPricelist, currency);
    }
	@Override
	public Price findProductPrice(AbstractProduct product, Pricelist priceList , Currency currency) throws PriceNotFoundException{
		List<Price> prices = findValidPricesforProduct(product, priceList, currency);
		if( prices.isEmpty())
			throw  new PriceNotFoundException(product);
		
		return prices.get(0);
	}
	
	@Override
	public Price findProductPrice(AbstractProduct product, Currency currency)
			throws PriceNotFoundException {
		 final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
	     return findProductPrice(product, defaultPricelist, currency);
	}
	@Override
	public Price findProductPrice(AbstractProduct product) throws PriceNotFoundException{

		 final Currency currency = (Currency) getCurrentContext().getCurrency();
		 final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
		 return findProductPrice(product,defaultPricelist, currency);
	}

	@Override
	public AbstractProduct addPrice(AbstractProduct product, Price price, Pricelist pricelist) {
		Assert.notNull(product );
		Assert.notNull(price );
		Assert.notNull(pricelist );
		price.setPricelist(pricelist);
		price.setProduct(product);

		price = priceRepository.saveAndFlush(price);
		 productRepository.detach(product);
		 return productRepository.findOne(product.getId());
	}

	@Override
	public AbstractProduct addPrice(AbstractProduct product, Price price) {
		 final Pricelist defaultPricelist = priceListService.getCurrentPriceList();
	     
	        return addPrice(product, price, defaultPricelist);
	}
	
	
}
