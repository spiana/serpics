package com.serpics.base.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.Country;
import com.serpics.core.data.Repository;

public interface CountryRepository extends Repository<Country, Long> {

	@Query("select c from Country c where c.iso3Code = :iso3Code")
	public Country getCountryByIso3Code(@Param("iso3Code") String iso3Code);
	
}
