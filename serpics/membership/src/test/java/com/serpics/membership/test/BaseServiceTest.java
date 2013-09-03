package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.services.BaseService;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({  "classpath*:META-INF/applicationContext.xml"})
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {

	@Autowired
	BaseService b;
	
	@Autowired
	CommerceEngine commerceEngine;

	@Test
	@Transactional
	public void test() throws SerpicsException {
		b.initIstance();
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
