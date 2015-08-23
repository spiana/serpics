package com.serpics.membership.test;




import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
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
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.PermanentAddressService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
@SerpicsTest("default-store")
@Transactional
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
	GeoCodeRepository geoCodeRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	
	
	
	@Resource
	CommerceEngine ce;
	
	@Before
	public void beforeTest(){
		baseService.initIstance();
	
	}
	
	@Test
	public void createUser() throws SerpicsException{
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
		
		ce.connect("default-store", "vale01", "vale".toCharArray());
		updateUser();	
		billingUser();
		
		
		ce.connect("default-store", "vale02", "vale".toCharArray());
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
		
		List<BillingAddress> l2 = billingAddressService.findAll();
	}
	
	
	private void destinationAddressUser() throws SerpicsException{	
		
		Assert.assertEquals("Destination is not null" ,0, userFacade.getCurrentuser().getDestinationAddress().size());
		
		AddressData address = new AddressData();
		address.setAddress1("via di prova");
		address.setStreetNumber("1");
		userFacade.addDestinationAddress(address);
		
		
		address = new AddressData();
		address.setAddress1("via di prova forse");
		address.setStreetNumber("2");
		userFacade.addDestinationAddress(address);
		
		Assert.assertNotNull("Destination Address 1 is null" , userFacade.getCurrentuser().getDestinationAddress());
		List<AddressData> l = userFacade.getCurrentuser().getDestinationAddress();
		String uuid = l.get(0).getUuid();
		address = new AddressData();
		address.setAddress1("modifico address");
		address.setStreetNumber("2");
		address.setCity("intra");
		address.setCity("milano");
		userFacade.updateDestinationAddress(address, uuid);
		
		Assert.assertNotNull("Destination Address 1 cituyis null" , userFacade.getCurrentuser().getDestinationAddress().get(0).getCity());
		
		
		userFacade.deleteDestinationAddress(uuid);
		
		Assert.assertNotNull("Destination Address is 3  null" , userFacade.getCurrentuser().getDestinationAddress());
		
		List<PermanentAddress> l2 = permanentAddressService.findAll();
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
		r.setCountry(cd);
		r.setName("VB");
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
