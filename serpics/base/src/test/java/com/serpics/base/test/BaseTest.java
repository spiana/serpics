package com.serpics.base.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.base.facade.data.RegionData;
import com.serpics.core.SerpicsException;

import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

//@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@SerpicsTest("default-store")
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
	
	
	
	@Test
	@Transactional
	public void coutryregion()  throws SerpicsException{
		LOGGER.info("*** ENTRY COUTNRY REGION TEST ***");
		Geocode g = new Geocode();
		g.setCode("ITA");
		
		g = geoCodeRepository.create(g);
		
		Country c = new Country();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c = countryRepository.create(c);
		
		Region r = new Region();
		r.setName("TEST1");
		r.setCountry(c); 
		r = regionRepository.create(r);
		
		LOGGER.info("*** EXIT ****");
	}
}
