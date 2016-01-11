package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Currency;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.data.Repository;

public interface PriceRepository extends Repository<Price, Long> {

    @Query("select p from Price p where p.product=:product and pricelist = :priceList and p.currency=:currency and ((p.validFrom is null and p.validTo is null) or CURRENT_DATE between p.validFrom and p.validTo) order by precedence DESC")
    public List<Price> findValidPricesForProduct(@Param("product") Product product,
            @Param("priceList") Pricelist pricelist , @Param("currency") Currency currency);
    
    @Query("select p from Price p where p.product=:product and pricelist = :priceList and ((p.validFrom is null and p.validTo is null) or CURRENT_DATE between p.validFrom and p.validTo) order by precedence DESC")
    public List<Price> findValidPricesForProduct(@Param("product") Product product,
            @Param("priceList") Pricelist pricelist );
}
