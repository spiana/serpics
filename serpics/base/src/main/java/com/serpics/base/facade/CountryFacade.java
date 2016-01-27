package com.serpics.base.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.CountryData;



public interface CountryFacade {
	public CountryData addCountry(CountryData country);
	 
	public Page<CountryData> findAll(Pageable page);
	
	public CountryData findCountryByUuid(String countryUuid);
}
