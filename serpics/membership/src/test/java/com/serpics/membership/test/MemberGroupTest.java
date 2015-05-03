package com.serpics.membership.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.repositories.MemberGroupRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.test.ExecutionTestListener;


@ContextConfiguration({ "classpath*:META-INF/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class,
    DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class MemberGroupTest {
	
	@Autowired
	BaseService b;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Autowired
	MemberGroupRepository memberGroupRepository;

	@Test
	public void test() throws SerpicsException{
		b.initIstance();
		CommerceSessionContext context = commerceEngine.connect("default-store");
		
		Membergroup m = new Membergroup();
		m.setName("test");
		m.setStore((Store) context.getStoreRealm());
		memberGroupRepository.create(m);
		
		List<Membergroup> l= memberGroupRepository.findAll();
		Assert.assertEquals(1, l.size());
		
	}
}
