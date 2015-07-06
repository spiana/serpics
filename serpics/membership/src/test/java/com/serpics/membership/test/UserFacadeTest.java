package com.serpics.membership.test;




import javax.annotation.Resource;

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
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.facade.UserFacade;
import com.serpics.membership.facade.data.UserData;
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
	
	@Autowired
	GeoCodeRepository geoCodeRepository;
	
	@Autowired
	CountryRepository countryrepository;
	
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
			
	}
	
	@Test
	@Transactional
	public void registUsertest() throws SerpicsException{
		
	//	ce.connect("default-store");
		UserData d = new UserData();
		d.setLogonid("registerUser");
		userFacade.registerUser(d);
		
	}
	
	private void createUser(String name){
		UsersReg u = new UsersReg();
		u.setLogonid(name);
		u.setPassword("1");
		u.setLastname(name);
		
		Geocode g = new Geocode();
		g.setCode("ITA");
		
		g = geoCodeRepository.create(g);
		
		
		Country c = new Country();
		c.setIso2Code("it");
		c.setIso3Code("ita");
		c.setGeocode(g);
		
		c = countryrepository.create(c);
		
		PrimaryAddress a = new PrimaryAddress();
		a.setAddress1("prova");
		a.setCountry(c);
		if(name.equals("test1")) a.setNickname("PROVA VALE NICK 1");
		userService.registerUser(u, a);
		
	}
	
	@Test
	@Transactional
	public void valeTest()  throws SerpicsException{
		System.out.println("*** STARTING TEST VALE ***") ;
		ce.connect("default-store" , "superuser" ,"admin".toCharArray() );
		//Page<UserData> l = userFacade.findAllUser(new PageRequest(0, 100));
	}
}
