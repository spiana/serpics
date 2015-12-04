package com.serpics.base.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.base.services.CountryService;
import com.serpics.base.services.RegionService;
import com.serpics.core.SerpicsException;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml"})
@SerpicsTest("default-store")
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@Ignore
public  class BaseTest  extends AbstractTransactionalJunit4SerpicTest{
	private static final Logger LOGGER = Logger.getLogger(BaseTest.class);
	
	@Autowired
	GeoCodeRepository geoCodeRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	RegionService regionService;
	
	@Test
	@Transactional
	public void coutryregion()  throws SerpicsException{
		LOGGER.info("*** ENTRY COUTNRY REGION TEST ***");
		Geocode g = new Geocode();
		g.setCode("ITA");
		
		g = geoCodeRepository.saveAndFlush(g);
		
		MultilingualString desc = new MultilingualString("it", "ITALIA");
        Country c = new Country();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c.setDescription(desc);
		c = countryRepository.saveAndFlush(c);
		
		desc = new MultilingualString("it", "GRAN BRETAGNA");
		c = new Country();
		c.setIso2Code("en");
		c.setIso3Code("eng");
		c.setGeocode(g);
		c.setDescription(desc);
		c = countryRepository.saveAndFlush(c);
		
		
		desc = new MultilingualString("it", "TEST");
		Region r = new Region();
		r.setName("TEST1");
		r.setCountry(c); 
		r.setDescription(desc);
		r = regionRepository.saveAndFlush(r);
		
		
		List<Country> lc =  countryRepository.findAll();
		for (Country country : lc) {
			LOGGER.info("*** ELENCO COUNTRY ****");
			LOGGER.info(country.getIso2Code() + "-" + country.getDescription().getText("it"));
		}
		
		LOGGER.info("*** EXIT ****");
		
		String uuid = null;
		 lc =  countryService.findAll();
		 for (Country country : lc) {
			LOGGER.info("*** ELENCO COUNTRY ****");
			LOGGER.info(country.getIso2Code());
			uuid = country.getUuid();
		}
		
		 
		 Country cu = countryService.findByUUID(uuid);
		 LOGGER.info("uiid " + cu.getIso2Code());
		 
		 
		 List<Region> lr = regionService.findAll();
		 for (Region  region: lr) {
				LOGGER.info("REGIONE" + region.getId() + "-" + region.getName() + " - " + region.getDescription().getText("it"));
		
		}
	}
	
	
	
}
