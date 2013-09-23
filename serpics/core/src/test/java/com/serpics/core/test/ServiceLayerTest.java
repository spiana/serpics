package com.serpics.core.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.core.CommerceEngineFactory;
import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.core.test.service.TestService;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({  "classpath*:META-INF/core-serpics.xml"})
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@SerpicsTest("store2")
public class ServiceLayerTest extends Assert {

	@Autowired
	TestService s ;
	
	
	
	@Test
	public void storeServiceSelect(){
		
		ApplicationContext applicationContext = CommerceEngineFactory.getCurrentApplicationContext();
		StoreScopeContextHolder.setCurrentStoreRealm("store1");
		TestService s1 = applicationContext.getBean(TestService.class);
		assertEquals("TestService1", s1.getServiceClassName());
		
		StoreScopeContextHolder.setCurrentStoreRealm("store2");
		TestService s2 = applicationContext.getBean(TestService.class);
		assertEquals("TestService4", s2.getServiceClassName());
		
		StoreScopeContextHolder.setCurrentStoreRealm("store3");
		TestService s3 = applicationContext.getBean(TestService.class);
		assertEquals("TestService4", s3.getServiceClassName());
		
		// Test Autowired
		assertEquals("TestService4", s.getServiceClassName());
		
	}
	
	@Test(expected = BeanCreationException.class)
	public void test2 (){
		ApplicationContext applicationContext = CommerceEngineFactory.getCurrentApplicationContext();
		
		StoreScopeContextHolder.setCurrentStoreRealm("default-store");
		TestService s4 = applicationContext.getBean(TestService.class);
		assertEquals("TestService4", s4.getServiceClassName());
	}
	
}
