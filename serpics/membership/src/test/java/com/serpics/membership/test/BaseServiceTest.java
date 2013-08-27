package com.serpics.membership.test;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.services.BaseService;

@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {

	@Resource(name="baseService")
	BaseService baseService;
	@Resource
	CommerceEngine commerceEngine;

	@Test
	@Transactional
	public void test() throws SerpicsException {
		baseService.initIstance();
		CommerceSessionContext context = commerceEngine.connect("default-store");
		assertNotNull(context);
	}
}
