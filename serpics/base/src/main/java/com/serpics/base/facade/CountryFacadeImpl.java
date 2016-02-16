package com.serpics.base.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.services.CountryService;
import com.serpics.base.services.GeocodeService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.Engine;
import com.serpics.core.facade.AbstractPopulatingConverter;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("countryFacade")
@Transactional(readOnly=true)
public class CountryFacadeImpl implements CountryFacade {
	@Autowired
	CountryService countryService;
	
	@Autowired
	GeocodeService geocodeService;
	
	@Resource(name="countryConverter")
	AbstractPopulatingConverter<Country, CountryData> countryConvert;
	
	@Autowired
	Engine<CommerceSessionContext> engine;
	
	@Override
	@Transactional
	public CountryData addCountry(CountryData data) {
		Geocode g =  geocodeService.findByUUID(data.getGeocode().getUuid());
		String locale = "it";
		//if(engine.getCurrentContext() != null) locale = engine.getCurrentContext().getLocale().getLanguage();
		final MultilingualString desc = new MultilingualString(locale, data.getDescription());
		Country c = new Country();
		c.setIso2Code(data.getIso2Code());
		c.setIso3Code(data.getIso3Code());
		c.setGeocode(g);
		c.setDescription(desc);
		c = countryService.create(c);
		CountryData ncd = countryConvert.convert(c);
		Assert.notNull(ncd);
		return ncd ;
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

	@Override
	public CountryData findCountryByUuid(String countryUuid) {
		
		return countryConvert.convert(countryService.findByUUID(countryUuid));
		
	}
	
	@Override
	public CountryData findCountryByIso3Code(String iso3Code) {
		
		return countryConvert.convert(countryService.getCountryByIso3Code(iso3Code));
		
	}
	
	@Override
	public List<CountryData> findAllList() {
		List<Country> countries = countryService.findAll(); 
		
		List<CountryData> countriesData = new ArrayList<CountryData>();
		for (Country country : countries) {
			countriesData.add(countryConvert.convert(country));
		}
		
		return countriesData;
	}
}