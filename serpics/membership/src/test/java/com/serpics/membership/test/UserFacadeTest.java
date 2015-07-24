package com.serpics.membership.test;




import java.util.ArrayList;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.model.Region;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.RegionRepository;
import com.serpics.base.facade.CountryFacade;
import com.serpics.base.facade.RegionFacade;
import com.serpics.base.facade.data.CountryData;
import com.serpics.base.facade.data.RegionData;
import com.serpics.base.services.CountryService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.UserType;
import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.UserRegrepository;
import com.serpics.membership.data.repositories.UserRepository;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.AddressData;
import com.serpics.membership.facade.data.UserData;
import com.serpics.membership.services.AddressService;
import com.serpics.membership.services.BaseService;
import com.serpics.membership.services.BillingAddressService;
import com.serpics.membership.services.UserService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;


@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@SerpicsTest("default-store")
@Transactional
public class UserFacadeTest extends AbstractTransactionalJunit4SerpicTest{
	private static final Logger LOGGER = Logger.getLogger(UserFacadeTest.class);

	@Resource
	CountryFacade countryFacade;
	
	@Resource
	RegionFacade regionFacade;
	
	@Resource
	BaseService baseService;
	
	@Resource
	UserFacade userFacade;
	
	@Resource
	UserService userService ;

	
	
	
	@Resource
	CountryService countryService;
	
	
	
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
	BillingAddressService billingAddressService;
	
	@Resource
	UserRegrepository ur;
	
	@Resource
	CommerceEngine ce;
	
	@Before
	public void beforeTest(){
		baseService.initIstance();
	
	}
	
	@Test
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
	public void registUsertest() throws SerpicsException{
	//	ce.connect("default-store");
		Geocode g = new Geocode();
		g.setCode("GPN");
		g = geoCodeRepository.create(g);
		
		CountryData c = new CountryData();
		c.setIso2Code("jp");
		c.setIso3Code("jpa");
		c.setGeocode(g);
		c = countryFacade.addCountry(c);
		
		
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
	
	
	

	public void createUserData() {
		UserData ud = null;
		AddressData ad = null;
		try{
			ud = new UserData();
			ud.setFirstname("vale facade 1");
			ud.setLastname("ranc");
			ud.setLogonid("vale01");
			ud.setPassword("prova");
			ud.setUserType(UserType.REGISTERED);
			
			ad = new AddressData();
			ad.setAddress1("via subito");
			ad.setStreeNumber("1");
			ad.setCity("VB");
			ud.setBillingAddress(ad);
			
			
			ad = new AddressData();
			ad.setAddress1("via di contatto è il primary");
			ad.setCity("VB");
			ud.setContactAddress(ad);
			
			
			userFacade.registerUser(ud);
		}catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		try{
			AddressData ba = new AddressData();
			ba.setAddress1("via di fattura");
			ba.setStreeNumber("12");
			ba.setZipcode("234234");
			ba.setCity("Pallanza");
			
			
			List<AddressData> lad = new ArrayList<AddressData>();
			AddressData pa = new AddressData();
			pa.setAddress1("per 1");
			lad.add(pa);
			
			pa = new AddressData();
			pa.setAddress1("per 2");
			lad.add(pa);
			
			ud = new UserData();
			ud.setFirstname("vale facade 2");
			ud.setLastname("ranc2");
			ud.setEmail("prova@p.it");
			ud.setLogonid("vale02");
			ud.setUserType(UserType.GUEST);
			ud.setBillingAddress(ba);
			ud.setDestinationAddress(lad);
			userFacade.registerUser(ud);
			
			
			
		}catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		try{
			ud = new UserData();
			ud.setFirstname("valentina");
			ud.setLastname("rancilio");
			ud.setEmail("vrancilio@stepfour.it");
			ud.setLogonid("vale03");
			ud.setPhone("123");
			userFacade.registerUser(ud);
		}catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		
		
	}
	
	private void listUserData() {
		Page<UserData> lu = userFacade.findAllUser(new PageRequest(0, 10));
		for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			UserData u = (UserData) iterator.next();
			LOGGER.info("UTENTE: " + u.getFirstname() + u.getUserType());
		}
		
		lu = userFacade.findAllUserByUserType(UserType.REGISTERED, new PageRequest(0, 10));
		for (Iterator iterator = lu.iterator(); iterator.hasNext();) {
			UserData u = (UserData) iterator.next();
			//u.setEmail("prova");
			u.setPhone("111");
			//u.setUserType(UserType.GUEST);
			userFacade.updateUser(u.getUuid(),u);
			
			LOGGER.info("UTENTE typoreg : " + u.getFirstname() + u.getUserType() + u.getLogonid() );
			
		}
		
		lu = userFacade.findAllUserByUserType(UserType.GUEST, new PageRequest(0, 10));
		LOGGER.info("totale guest " + lu.getTotalElements());
		lu = userFacade.findAllUserByUserType(UserType.REGISTERED, new PageRequest(0, 10));
		LOGGER.info("totale REGISTERED " + lu.getTotalElements());
		
		
		Page<UserData> pud = userFacade.findUserByName("prova", new PageRequest(0, 10));
		
		
		UserData ud =  userFacade.findUserById(new Long("4"));
		
		LOGGER.info("utente " 
				+ ud.getLogonid() + "\n" 
				+ ud.getFirstname() + " " + ud.getLastname()  + "\n" 
				+ ud.getEmail()   + "\n" 
				+ ud.getPhone( )
			);
	}
	
	private void updateCurrentAddressData()throws SerpicsException{
		ce.connect("default-store", "vale01", "prova".toCharArray());
		try { 
			AddressData ab = userFacade.getCurrentuser().getBillingAddress();
			if(ab == null) ab = new AddressData();
			ab.setAddress1("Via Intra Premeno");
			ab.setCity("via di prova");;
			ab.setFax("123");
			userFacade.updateBillingAddress(ab);
			userFacade.updateContactAddress(ab);
		} catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
	}
	
	/**
	 * @function: updateUserAddressData
	 * @description: change address information for user (not logged user)
	 * @throws SerpicsException
	 */
	private void updateUserAddressData()throws SerpicsException{
		LOGGER.info("****SEARCH");
		//Page<UserData> pud = userFacade.findUserByName("prova@p.it", new PageRequest(0, 10));
//		Page<UserData> pud = userFacade.findUserByLogonid("prova@p.it", new PageRequest(0, 10));
		
		UsersReg  k = ur.findBylogonid("vale02");
		
		Assert.assertNotNull(k);
		
		//Page<UserData> pud = userFacade.findAllUser( new PageRequest(0, 10));
		//if(pud.getTotalElements() == 1) {
		
			
			String addressUuId = k.getUuid();
			
			User _k = userService.findByUUID(addressUuId);
			UserData _ad = userFacade.findUserById(k.getId());
			
			//Address _a = addressService.findByUUID(k.getPrimaryAddress().getUuid());
			
			try { 
				AddressData ab = new AddressData();
				ab.setAddress1("Via Intra Premeno");
				ab.setCity("via di prova");;
				ab.setFax("123");
				userFacade.updateAddress(k.getPrimaryAddress().getUuid(), ab);
				LOGGER.info("exit update");
			} catch(javax.validation.ConstraintViolationException _e) {
				messageExceptione(_e);
			}
	}
	
	

	/**
	 * @function: updateUserAddressData
	 * @description: change address information for user (not logged user)
	 * @throws SerpicsException
	 */
	private void updateUserBillingAddress() throws SerpicsException{
		Geocode g = createGeoCode();
		CountryData c = createCountry(g);
		createRegion(c);
		
		Page<RegionData> lr = regionFacade.findAll(new PageRequest(0,10));
		RegionData r = lr.getContent().get(0);
		
		UsersReg  k = ur.findBylogonid("vale02");
		Assert.assertNotNull(k);
		
			
			
			try { 
				AddressData ab = new AddressData();
				ab.setAddress1("Via Intra Premeno");
				ab.setCity("via di prova");;
				ab.setFax("123");
				ab.setCountry(c);
				ab.setRegion(r);
				ab.setFirstname("b1");
				ab.setLastname("r1");
				ab.setCompany("ranc");
				ab.setEmail("ranc@email.it");
				ab.setIdNumber("id num");
				ab.setNickname("billadd Ranc");
				ab.setPhone("1332342");
				ab.setFax("444");
				ab.setStreeNumber("29/A");
				ab.setVatcode("00030442323432");
				ab.setZipcode("234234");
				userFacade.updateBillingAddress(k.getBillingAddress().getUuid(), ab);
				LOGGER.info("exit update");
			} catch(javax.validation.ConstraintViolationException _e) {
				messageExceptione(_e);
			}
			
			Assert.assertNotNull("is null" , k.getBillingAddress().getStreetNumber());
			LOGGER.debug("street number " + k.getBillingAddress().getStreetNumber());
			String oldUuidBilling = "";
			Page<UserData> list = userFacade.findAllUser(new PageRequest(0,10));
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				UserData ud = (UserData) iterator.next();
				if(ud.getLogonid().equals("vale02")) {
					oldUuidBilling = ud.getBillingAddress().getUuid();
					 userFacade.deleteBillingAddress(ud);
					 userFacade.deleteContactAddress(ud);
				}
				
			}
			
			k = ur.findBylogonid("vale02");
			
			BillingAddress ab =  billingAddressService.findByUUID(oldUuidBilling);
			
			LOGGER.info("EXIT");;
			
		
	}
	
	
	/**
	 * @function: updateUserPermanentAddress
	 * @description: change address information for user (not logged user)
	 * @throws SerpicsException
	 */
	private void updateUserPermanentAddress() throws SerpicsException{
		Geocode g = createGeoCode();
		CountryData c = createCountry(g);
		createRegion(c);
		
		Page<RegionData> lr = regionFacade.findAll(new PageRequest(0,10));
		RegionData r = lr.getContent().get(0);
		
		UsersReg  k = ur.findBylogonid("vale02");
		Assert.assertNotNull(k);
		Set<PermanentAddress> list = k.getPermanentAddresses();
		int i = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			PermanentAddress pa = (PermanentAddress) iterator.next();
			LOGGER.info("init permaneten address " + i + " - " + pa.getUuid());
			i++;
			
		}
		String uuid = k.getPermanentAddresses().iterator().next().getUuid();
		LOGGER.info("primo " + uuid);
		try { 
			AddressData ab = new AddressData();
			ab.setAddress1("Via Intra Premeno");
			ab.setCity("via di prova");;
			ab.setFax("123");
			ab.setCountry(c);
			ab.setRegion(r);
			ab.setFirstname("b1");
			ab.setLastname("r1");
			ab.setCompany("ranc");
			ab.setEmail("ranc@email.it");
			ab.setIdNumber("id num");
			ab.setNickname("billadd Ranc");
			ab.setPhone("1332342");
			ab.setFax("444");
			ab.setStreeNumber("29/A");
			ab.setVatcode("00030442323432");
			ab.setZipcode("234234");
			userFacade.updatePermanentAddress(uuid, ab);
			LOGGER.info("exit update");
		} catch(javax.validation.ConstraintViolationException _e) {
			messageExceptione(_e);
		}
		
		uuid = k.getPermanentAddresses().iterator().next().getUuid();
		LOGGER.info("secondo " + uuid);
		
		
		//userFacade.deletePermanentAddress(uuid);
		UsersReg  t = ur.findBylogonid("vale02");
		Assert.assertNotNull(t);
		Set<PermanentAddress> liste = t.getPermanentAddresses();
		i = 0;
		for (Iterator iterator = liste.iterator(); iterator.hasNext();) {
			PermanentAddress pa = (PermanentAddress) iterator.next();
			LOGGER.info("permaneten address " + i + " - " + pa.getUuid());
			i++;
			
		}
		LOGGER.info("EXIT PERMANENT");
	}
	@Test
	@Transactional(readOnly=true)
	public void userFacade()  throws SerpicsException{
		createUserData();
		listUserData();
//		updateCurrentAddressData();
//		updateUserAddressData();
		updateUserBillingAddress();
		//		updateUserPermanentAddress();
	}
	
	private Geocode createGeoCode() {
		Geocode g = new Geocode();
		g.setCode("ITA");
		g = geoCodeRepository.create(g);
		return g;
	}
	private CountryData createCountry(Geocode g){
		CountryData c = new CountryData();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		c =  countryFacade.addCountry(c);
		return c;
	}
	
	private void createRegion(CountryData cd) {
	
		Country c = countryService.findByUUID(cd.getUuid());
		Region r = new Region();
		r.setCountry(c);
		r.setName("VB");
		r.setDescription(new MultilingualString("ita", "Verbano-Cusio-Ossola"));
		r = regionRepository.create(r);
	}
	
	private void messageExceptione(javax.validation.ConstraintViolationException _e) {
		for (Iterator iterator = _e.getConstraintViolations().iterator(); iterator.hasNext();) {
			ConstraintViolation<ConstraintViolation> err = (ConstraintViolation) iterator.next();
			LOGGER.info("	ERROR " + err.getMessage() + "-- " + err.getInvalidValue()  + "-- " + err.getPropertyPath());
			
		}
	}
}
