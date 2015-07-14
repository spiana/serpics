package com.serpics.membership.test;




import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;



















import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.services.CountryService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.AddressType;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.data.repositories.UserSpecification;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.AddressService;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@SerpicsTest("default-store")
public class UserFacadeTest extends AbstractTransactionalJunit4SerpicTest{
	private static final Logger LOGGER = Logger.getLogger(UserFacadeTest.class);

	
	
	@Resource
	BaseService baseService;
	
	@Resource
	UserFacade userFacade;
	
	@Resource
	UserService userService ;
	
	@Resource
	CountryService countryService;
	
	@Resource
	CountryFacade countryFacade;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Resource
	AddressService addressService;
	
	@Autowired
	GeoCodeRepository geoCodeRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Resource
	CommerceEngine ce;
	
	@Before
	@Transactional
	public void beforeTest(){
		baseService.initIstance();
	
	}
	
	@Test
	@Transactional
	public void testGetuser() throws SerpicsException{
				
			ce.connect("default-store" , "superuser" ,"admin".toCharArray() );
			Page<UserData> l = userFacade.findAllUser(new PageRequest(0, 100));
			
			Assert.assertEquals(1, l.getContent().size());
			Assert.assertEquals("Superuser", l.getContent().get(0).getLastname());
			Assert.assertEquals("Superuser",userFacade.getCurrentuser().getLastname());
	
			
			createUser("test1");
			ce.connect("default-store", "test1", "1".toCharArray());
			Assert.assertEquals("test1",userFacade.getCurrentuser().getLastname());
	
			ce.connect("default-store", "test1", "1".toCharArray());
			ce.connect("default-store" , "superuser" ,"admin".toCharArray() );
			createUser("test2");
			
			ce.connect("default-store", "test2", "1".toCharArray());
			Assert.assertEquals("test2",userFacade.getCurrentuser().getLastname());
			
			Page<UserData> l1 = userFacade.findAllUser(new PageRequest(0, 100));
			Assert.assertEquals(3, l1.getContent().size());
			Assert.assertEquals("it", l1.getContent().get(2).getContactAddress().getCountry().getIso2Code());
			
			LOGGER.info("**log PRIMO UTENTE " + l1.getContent().get(1).getContactAddress().getNickname());
			LOGGER.info("**log SECONDO UTENTE " + l1.getContent().get(2).getContactAddress().getNickname());
			for (Iterator iterator = l1.iterator(); iterator.hasNext();) {
				UserData ud = (UserData) iterator.next();
				LOGGER.info("*********");
				LOGGER.info("** " + ud.getEmail()  );
				LOGGER.info("** " + ud.getContactAddress().getNickname()  );
				if(ud.getContactAddress().getCountry() != null) LOGGER.info("**  " + ud.getContactAddress().getCountry().getIso2Code() );
				if(ud.getContactAddress().getRegion() != null) LOGGER.info("** " + ud.getContactAddress().getRegion().getName());
				if(ud.getContactAddress().getRegion() != null) LOGGER.info("** " + ud.getContactAddress().getRegion().getCountry().getIso2Code() );
				LOGGER.info("*********");
			}
	}
	
	@Test
	@Transactional
	public void registUsertest() throws SerpicsException{
	//	ce.connect("default-store");
		Geocode g = new Geocode();
		g.setCode("GPN");
		g = geoCodeRepository.create(g);
		
		CountryData c = new CountryData();
		c.setIso2Code("jp");
		c.setIso3Code("jpa");
		c.setGeocode(g);
		Country ct = countryFacade.addCountry(c);
		
		
		Page<CountryData> l = countryFacade.findAll(new PageRequest(0, 100));
		CountryData cd =  new CountryData();
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			cd = (CountryData) iterator.next();
		}
			
		
		AddressData a = new AddressData();
		a.setAddress1("prova");
		a.setCountry(cd);
		
		
		UserData d = new UserData();
		d.setLogonid("registerUser");
		d.setBillingAddress(a);
		userFacade.registerUser(d);
		
	}
	
	private void createUser(String name){
		UsersReg u = new UsersReg();
		u.setLogonid(name);
		u.setPassword("1");
		u.setLastname(name);
		u.setEmail("testprova@"+name+".it");
		Geocode g = new Geocode();
		g.setCode("ITA");
		
		g = geoCodeRepository.create(g);
		
		
		Country c = new Country();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c = countryRepository.create(c);
		
		Region r = new Region();
		r.setCountry(c);
		r.setName("Verbania");
		r = regionRepository.create(r);
		
		PrimaryAddress a = new PrimaryAddress();
		a.setAddress1("prova");
		a.setCountry(c);
		a.setRegion(r);
		if(name.equals("test1")) a.setNickname("PROVA VALE NICK 1");
		userService.registerUser(u, a);
		
	}
	
	@Test
	@Transactional
	public void valeTestService()  throws SerpicsException{
		System.out.println("*** STARTING TEST VALE ***") ;
		ce.connect("default-store" , "superuser" ,"admin".toCharArray() );
		UsersReg u = null;
		//CREAZIONE UTENTE REGISTRATO
		try{
			 u = new UsersReg();
			u.setFirstname("vale");
			u.setLastname("rancilio");
			
			u.setLogonid("vale76");
			u.setPassword("prova");
			u.setChangequestion("prova domanda");
			u.setChangeanswer("proviamo");
			
			Geocode g = createGeoCode();
			Country c = createCountry(g);
			PrimaryAddress pa = new PrimaryAddress();
			pa.setAddress1("via di pprova");
			pa.setCity("verbania");
			pa.setCountry(c);
			pa.setMobile("347");
			pa.setFax("00");
			pa.setEmail("prova emaila");
			
			u = userService.registerUser(u, pa);
			
			BillingAddress ba = new BillingAddress();
			ba.setAddress1("indi pa1");
			ba.setCity("Intra");
			ba.setCountry(c);
			ba.setZipcode("12");
			ba.setNickname("billingadr");
			userService.addBillingAddress(ba, u);
			
			
			PermanentAddress pea = new PermanentAddress();
			pea.setAddress1("peramanet 1");
			pea.setCity("intra");
			pea.setFlag(AddressType.PERMANENT);
			userService.addAddress(pea, u);
			
			pea = new PermanentAddress();
			pea.setAddress1("peramenten 2");
			pea.setCity("pallanza");
			userService.addAddress(pea, u);
			
			
			
			
			LOGGER.info(u.getLogonid());
			
			
		} catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
			
		}
		
		List<User> lu = userService.findAll();
		for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			LOGGER.info( user.getName() + "-" + user.getUserType());
		}
		
		lu = userService.findAll(UserSpecification.findByUserType(UserType.REGISTERED),new PageRequest(0, 10));
		for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			LOGGER.info( user.getName() + "-" + user.getUserType());
		}
		//LOGGO CON UTENT
		ce.connect("default-store", "vale76", "prova".toCharArray());
		
		//utente corrent  AGGIORNO I DATI service
		User cu = userService.getCurrentUser();
		BillingAddress ba = cu.getBillingAddress();
		PrimaryAddress pa = cu.getPrimaryAddress();
		Set<PermanentAddress> spa = cu.getPermanentAddresses();
		try {
			cu.setLastname("prova 2");
			cu.setFirstname("vale new");
			cu.setPhone("11");
			cu.setEmail("newemaili");
			userService.update(cu);
		} catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		try {
			pa.setAddress1("new primary addr");
			userService.updatePrimaryAddress(pa);
		} catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		try {
			ba.setNickname("billingName");
			ba.setAddress1("new billing addresss");
			ba.setCity("nuova cita");
			userService.updateBillingAddress(ba);
		}catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		
		for (PermanentAddress permanentAddress : spa) {
			permanentAddress.setAddress1("updade peramente 1");
			permanentAddress.setCity("citi due");
			userService.updatePermanentAddress(permanentAddress);
		}
		LOGGER.info("UTENTE CORRENTE " + cu.getFirstname()  + cu.getName());
		LOGGER.info("EXIT");
	
	}
	
	@Test
	@Transactional
	public void valeTestFacade()  throws SerpicsException{
		//Creazione UTENTE
		LOGGER.debug("VALE FACADE TEST USER");
		UserData ud = new UserData();
		ud.setFirstname("vale facade 1");
		ud.setLastname("ranc");
		ud.setLogonid("falefa");
		ud.setUserType(UserType.REGISTERED);
		userFacade.registerUser(ud);
		
		ud = new UserData();
		ud.setFirstname("vale facade 2");
		ud.setLastname("ranc2");
		ud.setLogonid("falefa");
		ud.setUserType(UserType.GUEST);
		userFacade.registerUser(ud);
		
		Page<UserData> lu = userFacade.findAllUser(new PageRequest(0, 10));
		for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			UserData u = (UserData) iterator.next();
			LOGGER.info("UTENTE: " + u.getFirstname() + u.getUserType());
		}
		
		
		lu = userFacade.findAllUserByUserType(UserType.GUEST, new PageRequest(0, 10));
		for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			UserData u = (UserData) iterator.next();
			u.setEmail("prova");
			u.setUserType(UserType.REGISTERED);
			userFacade.updateUser(u.getUuid(),u);
			LOGGER.info("UTENTE typoreg : " + u.getFirstname() + u.getUserType());
			
		}
		
		lu = userFacade.findAllUserByUserType(UserType.GUEST, new PageRequest(0, 10));
		LOGGER.info("totale guest " + lu.getTotalElements());
		lu = userFacade.findAllUserByUserType(UserType.REGISTERED, new PageRequest(0, 10));
		LOGGER.info("totale REGISTERED " + lu.getTotalElements());
		
		
		LOGGER.info("PPROVA");
	}
	
	private Geocode createGeoCode() {
		Geocode g = new Geocode();
		g.setCode("ITA");
		
		g = geoCodeRepository.create(g);
		return g;
	}
	private Country createCountry(Geocode g){
		Country c = new Country();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c = countryRepository.create(c);
		return c;
	}
	
	private void messageExceptione(javax.validation.ConstraintViolationException _e) {
		for (Iterator iterator = _e.getConstraintViolations().iterator(); iterator.hasNext();) {
			ConstraintViolation<ConstraintViolation> err = (ConstraintViolation) iterator.next();
			LOGGER.info("	ERROR " + err.getMessage() + "-- " + err.getInvalidValue()  + "-- " + err.getPropertyPath());
			
		}
	}
}
