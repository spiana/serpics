package com.serpics.base.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.GeocodeFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@SerpicsTest("default-store")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTestFacade extends AbstractTransactionalJunit4SerpicTest{
	private static final Logger LOGGER = Logger.getLogger(BaseTestFacade.class);
	
	@Resource
	CountryFacade countryFacade;
	
	@Resource
	GeocodeFacade geocodeFacade;
	
	
	//@Autowired
	//BaseService baseService;
	
	
	 @Autowired
	  CommerceEngine ce;
	 
	CommerceSessionContext context;


	@Before
	public void beforeTest()throws SerpicsException {
		//baseService.initIstance();
		//ce.connect("default-store", "superuser", "admin".toCharArray());
	}
	
	private void country() throws SerpicsException{
		
		Page<GeocodeData>  l = geocodeFacade.findAll(new PageRequest(0,10));
		Assert.assertNotNull("List is null", l);
		
		GeocodeData g = l.getContent().get(0);
		
		CountryData c = new CountryData();
		c.setGeocode(g);
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setDescription("ITALIA");
		countryFacade.addCountry(c);
		 
		
		c = new CountryData();
		c.setGeocode(g);
		c.setIso2Code("uk");
		c.setIso3Code("UKG");
		c.setDescription("UNITED KINGDOM");
		countryFacade.addCountry(c);
	}
	
	
	private void geocode() throws SerpicsException{
		GeocodeData g = new GeocodeData();
		g.setCode("ISO1");
		g.setDescription("CODICE ISO 20303");
		g.setName("ISO 1 TEST");
		geocodeFacade.addGeocode(g);
		
		g = new GeocodeData();
		g.setCode("ISO2");
		g.setDescription("CODICE ISO 20303");
		g.setName("ISO 2 TEST");
		geocodeFacade.addGeocode(g);
		
		
		
	}
	
	private void list() {
		Page<GeocodeData> lg = geocodeFacade.findAll(new PageRequest(0,10));
		Assert.assertNotNull("Geo code list is null" , lg);
		
		
		Page<CountryData> lc = countryFacade.findAll(new PageRequest(0,10));
		Assert.assertNotNull("Coluntry list  is null" , lc);
		Assert.assertNotNull("not geocode set" , lc.getContent().get(0).getGeocode().getUuid());
	}
	
	@Test
	public void testGeocraphics() throws SerpicsException{
		geocode();
		country();
		list();
	}
}