package com.serpics.commerce.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.data.model.Tax;
import com.serpics.core.data.Repository;

public interface TaxRepository  extends Repository<Tax, Long>{
	
	@Query("select m from Taxlookup as t join t.tax as m where t.store = :store and t.active = 1")
	public List<Tax> findActiveTax(@Param("store") Store store);

}
