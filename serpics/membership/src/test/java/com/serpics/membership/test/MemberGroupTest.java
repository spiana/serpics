package com.serpics.membership.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
import com.serpics.membership.data.model.Membergroup;
import com.serpics.membership.data.repositories.MemberGroupRepository;
import com.serpics.membership.services.BaseService;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml" , "classpath:META-INF/membership-serpics.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MemberGroupTest extends AbstractTransactionalJunit4SerpicTest{
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Autowired
	MemberGroupRepository memberGroupRepository;

	@Test
	public void test() throws SerpicsException{
		if (!baseService.isInitialized())
			baseService.initIstance();
		CommerceSessionContext context = commerceEngine.connect("default-store");
		
		Membergroup m = new Membergroup();
		m.setName("test");
		m.setStore((Store) context.getStoreRealm());
		memberGroupRepository.saveAndFlush(m);
		
		List<Membergroup> l= memberGroupRepository.findAll();
		Assert.assertEquals(1, l.size());
		
	}
}
