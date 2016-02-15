package com.serpics.base.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.serpics.base.facade.data.CountryData;



public interface CountryFacade {
	public CountryData addCountry(CountryData country);
	 
	public Page<CountryData> findAll(Pageable page);
	
	public CountryData findCountryByUuid(String countryUuid);

	public List<CountryData> findAllList();

	public CountryData findCountryByIso3Code(String iso3Code);
}
