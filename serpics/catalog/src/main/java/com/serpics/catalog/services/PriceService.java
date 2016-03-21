package com.serpics.catalog.services;

import java.util.List;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.model.Product;
import com.serpics.core.service.EntityService;

public interface PriceService extends EntityService<Price, Long> {
	
    
    public List<Price> findValidPricesforProduct(Product product, Pricelist pricelist , Currency cuurency);
    public List<Price> findValidPricesforProduct(Product product);
    public Price findProductPrice(Product product , Pricelist priceList , Currency currency ) throws PriceNotFoundException;
    public Price findProductPrice(Product product) throws PriceNotFoundException;
    public Price findProductPrice(Product product, Currency currency) throws PriceNotFoundException;
    
    public Product addPrice(Product product , Price price , Pricelist priceList);
    public Product addPrice(Product product , Price price );
    
}
