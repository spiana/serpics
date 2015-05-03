package com.serpics.catalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.data.Repository;

public interface PriceRepository extends Repository<Price, Long> {

    @Query("select p from Price p where p.product=:product and pricelist = :priceList and ((p.validFrom is null and p.validTo is null) or CURRENT_DATE between p.validFrom and p.validTo) order by precedence DESC")
    public List<Price> findValidPricesForProduct(@Param("product") AbstractProduct product,
            @Param("priceList") Pricelist pricelist);
}
