package com.serpics.catalog.services;

import java.util.List;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Price;
import com.serpics.catalog.persistence.Pricelist;
import com.serpics.core.service.EntityService;

public interface PriceService extends EntityService<Price, Long> {

    public List<Price> findValidPricesforProduct(AbstractProduct product);

    public List<Price> findValidPricesforProduct(AbstractProduct product, Pricelist pricelist);

}
