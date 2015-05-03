package com.serpics.catalog.services;

import java.util.List;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.service.EntityService;

public interface PriceService extends EntityService<Price, Long> {

    public List<Price> findValidPricesforProduct(AbstractProduct product);

    public List<Price> findValidPricesforProduct(AbstractProduct product, Pricelist pricelist);

}
