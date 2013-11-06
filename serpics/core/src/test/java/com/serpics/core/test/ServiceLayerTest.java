package com.serpics.core.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.core.CommerceEngineFactory;
import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.core.test.service.MySecondTestService;
import com.serpics.core.test.service.TestService;
import com.serpics.core.test.service.MyTestService;
import com.serpics.core.test.service.TestServiceImpl1;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({  "classpath*:META-INF/core-serpics.xml"})
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@SerpicsTest("store4")
public class ServiceLayerTest extends Assert {

	@Autowired
	MyTestService s43 ;
	
	@Autowired
	TestService s44;
	
	
	@Test
	public void storeServiceSelect(){
		
		ApplicationContext applicationContext = CommerceEngineFactory.getCurrentApplicationContext();
		StoreScopeContextHolder.setCurrentStoreRealm("store4");
		MyTestService s4 =  applicationContext.getBean(MyTestService.class);
		assertEquals("hello", s4.sayHello());
		assertEquals("Good", s4.sayGood());
		
		TestService s41 = (TestService) applicationContext.getBean("testService");
		assertEquals("TestService1Impl", s41.getServiceClassName());
		
		TestService s42 = applicationContext.getBean("testService" , TestService.class);
		assertEquals("TestService1Impl", s42.getServiceClassName());
		// Autowired
		assertEquals("TestService1Impl", s43.getServiceClassName());
		assertEquals("TestService1Impl", s44.getServiceClassName());
		
		
		StoreScopeContextHolder.setCurrentStoreRealm("store1");
		TestService s1 = applicationContext.getBean(TestService.class);
		assertEquals("TestServiceImpl1", s1.getServiceClassName());
		assertEquals("is all good !", s1.sayGood());
		
		StoreScopeContextHolder.setCurrentStoreRealm("store2");
		TestService s2 = (TestService)applicationContext.getBean("testService");
		assertEquals("TestService2", s2.getServiceClassName());
		
		StoreScopeContextHolder.setCurrentStoreRealm("store5");
		MySecondTestService s5 = applicationContext.getBean(MySecondTestService.class);
		assertEquals("MySecondTestServiceImpl", s5.getServiceClassName());
	
		/*	
		StoreScopeContextHolder.setCurrentStoreRealm("store3");
		TestService s3 = applicationContext.getBean(TestService.class);
		assertEquals("TestService4", s3.getServiceClassName());
		*/
		// Test Autowired
//		assertEquals("hello", s.sayHello());
//		
//		StoreScopeContextHolder.setCurrentStoreRealm("store4");
//		TestService1 s4 = (TestService1) applicationContext.getBean(TestService.class);
//		assertEquals("hello", s4.sayHello());
	}
	
	@Test(expected = BeanCreationException.class)
	public void test2 (){
		ApplicationContext applicationContext = CommerceEngineFactory.getCurrentApplicationContext();
		
		StoreScopeContextHolder.setCurrentStoreRealm("default-store");
		TestService s4 = applicationContext.getBean(TestService.class);
		assertEquals("TestService4", s4.getServiceClassName());
	}
	@Test(expected=BeanNotOfRequiredTypeException.class)
	public void test3 (){
		ApplicationContext applicationContext = CommerceEngineFactory.getCurrentApplicationContext();
		
		StoreScopeContextHolder.setCurrentStoreRealm("store2");
		MyTestService s4 = applicationContext.getBean(MyTestService.class);
		assertEquals("TestService2", s4.getServiceClassName());
	}
	@Test(expected=ClassCastException.class)
	public void test4 (){
		ApplicationContext applicationContext = CommerceEngineFactory.getCurrentApplicationContext();
		
		StoreScopeContextHolder.setCurrentStoreRealm("store2");
		MyTestService s4 = (MyTestService) applicationContext.getBean("testService");
		assertEquals("TestService2", s4.getServiceClassName());
	}
}

