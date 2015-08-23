package com.serpics.membership.test;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Currency;
import com.serpics.base.data.repositories.CurrencyRepository;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.membership.data.repositories.StoreRepository;
import com.serpics.membership.data.repositories.UserRegrepository;
import com.serpics.membership.services.BaseService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({  "classpath*:META-INF/applicationContext-test.xml"})
@Transactional
@SerpicsTest("defualt-store")
public class RepositoryTest extends AbstractTransactionalJunit4SerpicTest {
	@Autowired
	BaseService baseService;
	@Autowired
	UserRegrepository userRegrepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	CurrencyRepository currencyRepository;
	
	@Autowired
	CommerceEngine ce ;
	
	@Before
	public void beforeTest(){
		baseService.initIstance();
	}
	
	@Test
	public void userRegTest() throws SerpicsException{
		ce.connect("default-store");
		
		UsersReg u = new UsersReg();
		u.setFirstname("firstname");
		u.setLastname("lastname");
		userRegrepository.create(u);
		
		List<UsersReg> l = userRegrepository.findAll();
		Assert.assertEquals(2,l.size());
		
		createStore();
		
		ce.connect("test-store");
		UsersReg u1 = new UsersReg();
		u1.setFirstname("firstname");
		u1.setLastname("lastname");
		userRegrepository.create(u1);
		
		List<UsersReg> l1 = userRegrepository.findAll();
		Assert.assertEquals(1,l1.size());
		
	}
	
	private void createStore(){
		Currency example = new Currency();
		example.setIsoCode("EUR");
		Currency c = currencyRepository.findOne(currencyRepository.makeSpecification(example));
		Store _s = new Store();
		_s.setName("test-store");
		_s.setCurrency(c);
		storeRepository.saveAndFlush(_s);
	}
}
