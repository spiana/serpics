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
package com.serpics.catalog.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.core.data.Repository;
import com.serpics.i18n.data.model.Currency;

public interface PriceRepository extends Repository<Price, Long> {

    @Query("select p from Price p where p.product=:product and pricelist = :priceList and p.currency=:currency and ((p.validFrom is null and p.validTo is null) or CURRENT_DATE between p.validFrom and p.validTo) order by precedence DESC")
    public List<Price> findValidPricesForProduct(@Param("product") AbstractProduct product,
            @Param("priceList") Pricelist pricelist , @Param("currency") Currency currency);
    
    @Query("select p from Price p where p.product=:product and pricelist = :priceList and ((p.validFrom is null and p.validTo is null) or CURRENT_DATE between p.validFrom and p.validTo) order by precedence DESC")
    public List<Price> findValidPricesForProduct(@Param("product") AbstractProduct product,
            @Param("priceList") Pricelist pricelist );
}
