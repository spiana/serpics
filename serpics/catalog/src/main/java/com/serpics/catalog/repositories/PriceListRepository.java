package com.serpics.catalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.Catalog;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.data.Repository;

public interface PriceListRepository extends Repository<Pricelist, Long> {

    @Query("select pr from Pricelist pr where catalog = :catalog and defaultList=1")
    public List<Pricelist> findDefaultList(@Param("catalog") Catalog catalog);

}
