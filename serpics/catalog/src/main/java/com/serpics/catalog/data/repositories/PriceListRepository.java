package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.data.Repository;

public interface PriceListRepository extends Repository<Pricelist, Long> {

    @Query("select pr from Pricelist pr where store = :store and defaultList=1")
    public List<Pricelist> findDefaultList(@Param("store") Store store);

}
