package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.BaseService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {

	@Autowired
	BaseService b;
	@Autowired
	CommerceEngine ce;

	@Test
	@Transactional
	public void test() throws SerpicsException {
		b.initIstance();
		CommerceSessionContext context = ce.connect("default-store");
		assertNotNull(context);
	}
}
