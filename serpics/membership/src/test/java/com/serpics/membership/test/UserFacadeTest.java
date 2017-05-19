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
package com.serpics.membership.test;




import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.GeocodeFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.GeocodeData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.base.services.CountryService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.i18n.data.model.Locale;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ "classpath:META-INF/i18n-serpics.xml" , "classpath:META-INF/base-serpics.xml" ,"classpath:META-INF/postman-serpics.xml" , "classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SerpicsTest("default-store")

public class UserFacadeTest extends AbstractTransactionalJunit4SerpicTest{
	private static final Logger LOGGER = Logger.getLogger(UserFacadeTest.class);

	@Resource
	CountryFacade countryFacade;
	
	@Resource
	RegionFacade regionFacade;
	
	@Resource
	GeocodeFacade geocodeFacade;
	
	@Resource
	BaseService baseService;
	
	@Resource
	CountryService countryService;
	
	@Resource
	BillingAddressService billingAddressService;
	
	@Resource
	PermanentAddressService permanentAddressService;
	
	@Resource
	UserFacade userFacade;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LocaleRepository localeRepository;
	
	
	
	@Autowired
	GeoCodeRepository geoCodeRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	
	
	
	@Resource
	CommerceEngine ce;
	
	@Before
	public void beforeTest(){
		if (!baseService.isInitialized())
			baseService.initIstance();
	
	}
	
	@Test
	@Transactional
	public void createUser() throws SerpicsException{
		Locale locale = localeRepository.findByLanguage("en");
		
		CommerceSessionContext context = ce.connect("default-store");
		context.setLocale(locale);
		
		GeocodeData g = createGeoCode();
		CountryData c = createCountry(g);
		
		
		UserData user = new UserData();
		user.setFirstname("vale01");
		user.setLastname("ranc");
		user.setChangequestion("domanda");
		user.setChangeanswer("risposta");
		user.setPhone("0323");
		
		user.setLogonid("vale01");
		user.setPassword("vale");
		userFacade.registerUser(user);
		
		user = new UserData();
		user.setFirstname("vale02");
		user.setLastname("ranc");
		user.setChangequestion("domanda 2");
		user.setChangeanswer("risposta 2");
		user.setPhone("0323");
		user.setLogonid("vale02");
		user.setPassword("vale");
		
		
		AddressData _a = new AddressData();
		_a.setAddress1("via di prova ");
		_a.setStreetNumber("2");
		_a.setCity("verbania");
		_a.setCountry(c);
		_a.setFax("999");
		_a.setEmail("vale02.@prova.it");
		_a.setMobile("347");
		_a.setZipcode("28921");
		user.setContactAddress((AddressData) _a);
		userFacade.registerUser(user);
		
		context = ce.connect("default-store", "vale01", "vale".toCharArray());
		context.setLocale(locale);
		
		updateUser();	
		billingUser();
		
		
		context = ce.connect("default-store", "vale02", "vale".toCharArray());
		context.setLocale(locale);
		destinationAddressUser();
		
		
		
		
	}
	
	
	private void updateUser() throws SerpicsException{
		
		
		UserData user = userFacade.getCurrentuser();
		Assert.assertNotNull("user is null" , user);
		Assert.assertEquals("vale01", user.getFirstname());
		
		user.setAlternateEmail("secondaem@prova.it");
		user.setChangequestion("nuovadomanda");
		user.setChangeanswer("nuova risposta");
		userFacade.updateUser(user);
		
		Assert.assertNull("non null primary", user.getContactAddress().getAddress1());
		AddressData address = new AddressData();
		address.setAddress1("via di prova");
		address.setStreetNumber("3");
		userFacade.updateContactAddress(address);
		
		Assert.assertNotNull("contact Address is null" , userFacade.getCurrentuser().getContactAddress());
	} 
	
	
	private void billingUser() throws SerpicsException{	
		AddressData address = new AddressData();
		address.setAddress1("via di prova");
		address.setStreetNumber("3");
		address.setCompany("ranc");
		address.setVatcode("32423");
		userFacade.addBillingAddress(address);
		
		Assert.assertNotNull("Billing Address 1 is null" , userFacade.getCurrentuser().getBillingAddress());
		
		address = new AddressData();
		address.setAddress1("modifico address");
		address.setStreetNumber("1");
		address.setCity("intra");
		userFacade.updateBillingAddress(address);
		
		Assert.assertNotNull("Billing Address 1 cituyis null" , userFacade.getCurrentuser().getBillingAddress().getCity());
		
		
		userFacade.deleteBillingAddress();
		
		Assert.assertNull("Billing Address is 2  null" , userFacade.getCurrentuser().getBillingAddress());
		
//		List<BillingAddress> l2 = billingAddressService.findAll();
	}
	
	
	private void destinationAddressUser() throws SerpicsException{	
		
		Assert.assertEquals(0, userFacade.getCurrentuser().getDestinationAddress().size());
		
		AddressData address = new AddressData();
		address.setAddress1("via di prova");
		address.setStreetNumber("1");
		userFacade.addDestinationAddress(address);
		
		
//		address = new AddressData();
//		address.setAddress1("via di prova forse");
//		address.setStreetNumber("2");
//		userFacade.addDestinationAddress(address);
		
		List<AddressData> l = userFacade.getCurrentuser().getDestinationAddress();
		Assert.assertEquals(1, l.size());
		Long id = l.get(0).getId();
		address = new AddressData();
		address.setAddress1("modifico address");
		address.setStreetNumber("2");
		address.setCity("intra");
		address.setCity("milano");
		userFacade.updateDestinationAddress(address, id);
		
		Assert.assertNotNull("Destination Address 1 cituyis null" , userFacade.getCurrentuser().getDestinationAddress().get(0).getCity());
		
		
		userFacade.deleteDestinationAddress(id);
		
		Assert.assertNotNull("Destination Address is 3  null" , userFacade.getCurrentuser().getDestinationAddress());
		
//		List<PermanentAddress> l2 = permanentAddressService.findAll();
	}
	private GeocodeData createGeoCode() {
		GeocodeData g = new GeocodeData();
		g.setName("ITA");
		g = geocodeFacade.addGeocode(g);
		return g;
	}
	private CountryData createCountry(GeocodeData g){
		CountryData c = new CountryData();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c =  countryFacade.addCountry(c);
		return c;
	}
	
	private void createRegion(CountryData cd) {
	
		RegionData r = new RegionData();
		r.setIsoCode("VB");
		r.setDescription("Verbano-Cusio-Ossola");
		r = regionFacade.addRegion(r);
	}
	
	private void messageExceptione(javax.validation.ConstraintViolationException _e) {
		for (Iterator iterator = _e.getConstraintViolations().iterator(); iterator.hasNext();) {
			ConstraintViolation<ConstraintViolation> err = (ConstraintViolation) iterator.next();
			LOGGER.info("	ERROR " + err.getMessage() + "-- " + err.getInvalidValue()  + "-- " + err.getPropertyPath());
			
		}
	}
}
