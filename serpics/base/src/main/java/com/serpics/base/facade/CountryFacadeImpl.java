package com.serpics.base.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Country;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.services.CountryService;
import com.serpics.core.facade.AbstractPopulatingConverter;

import com.serpics.stereotype.StoreFacade;

@StoreFacade("countryFacade")
@Transactional(readOnly=true)
public class CountryFacadeImpl implements CountryFacade {
	@Autowired
	CountryService countryService;
	
	@Resource(name="countryConverter")
	AbstractPopulatingConverter<Country, CountryData> countryConvert;
	
	@Override
	@Transactional
	public Country addCountry(CountryData cd) {
		Country c = new Country();
		c.setIso2Code(cd.getIso2Code());
		c.setIso3Code(cd.getIso3Code());
		c.setGeocode(cd.getGeocode());
		c = countryService.create(c);
		return c;
	}
	
	@Override
	public Page<CountryData> findAll(Pageable page) {
		Page<Country> countries = countryService.findAll(page); 
		
		List<CountryData> clist = new ArrayList<CountryData>();
		for (Country country : countries.getContent()) {
			clist.add(countryConvert.convert(country));
		}
		
		Page<CountryData> cdata= new PageImpl<CountryData>(clist ,page , countries.getTotalElements());
		return cdata;
	}
	
	
}