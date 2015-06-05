package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.core.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.membership.services.BaseService;

@ContextConfiguration({  "classpath*:META-INF/applicationContext.xml"})
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
