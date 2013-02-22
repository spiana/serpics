package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
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
import com.serpics.membership.repositories.UserRepository;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserFactoryTest {

	@Autowired
	UserRepository r;

	@Autowired
	BaseService b;

	@Autowired
	CommerceEngine c;

	@Before
	public void init() {
		b.initIstance();
	}

	@Test
	public void test() throws SerpicsException {

		CommerceSessionContext ctx = c.connect("default-store");
		assertNotNull(ctx);
	}

}
