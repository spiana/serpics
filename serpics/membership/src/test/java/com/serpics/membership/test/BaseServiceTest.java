package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , "classpath:META-INF/postman-serpics.xml" ,"classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BaseServiceTest  extends AbstractTransactionalJunit4SerpicTest{

	@Autowired
	BaseService b;
	
	@Autowired
	CommerceEngine commerceEngine;
	@Before
	
	public void beforeTest(){
		b.initIstance();
	}
	
	@Test
	public void test() throws SerpicsException {
		CommerceSessionContext context = commerceEngine.connect("default-store");
		assertNotNull(context);
	}

	public void setB(BaseService b) {
		this.b = b;
	}

	public void setCommerceEngine(CommerceEngine commerceEngine) {
		this.commerceEngine = commerceEngine;
	}
}
