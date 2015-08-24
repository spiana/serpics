package com.serpics.catalog.services;

import java.util.List;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.PriceNotFoundException;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.service.EntityService;

public interface PriceService extends EntityService<Price, Long> {
	public static String DEFAULT_LIST_NAME = "default-list";
	
    
    public List<Price> findValidPricesforProduct(AbstractProduct product, Pricelist pricelist , Currency cuurency);
    public List<Price> findValidPricesforProduct(AbstractProduct product);
    public Price findProductPrice(AbstractProduct product , Pricelist priceList , Currency currency ) throws PriceNotFoundException;
    public Price findProductPrice(AbstractProduct product) throws PriceNotFoundException;
    public Price findProductPrice(AbstractProduct product, Currency currency) throws PriceNotFoundException;
    
    public AbstractProduct addPrice(AbstractProduct product , Price price , Pricelist priceList);
    public AbstractProduct addPrice(AbstractProduct product , Price price );
 
    public Pricelist addPriceList(Pricelist priceList);
}
