package com.serpics.postman.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath:META-INF/applicationContext.xml", "classpath:META-INF/core-serpics.xml","classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
	"classpath:META-INF/catalog-serpics.xml", "classpath:META-INF/warehouse-serpics.xml",
	"classpath:META-INF/importexport-serpics.xml", "classpath:META-INF/scheduler-serpics.xml",
	"classpath:META-INF/postman-serpics.xml" })
@TestExecutionListeners({ ExecutionTestListener.class})
@TransactionConfiguration(defaultRollback = true )
public class EmailServiceTest extends AbstractTransactionalJunit4SerpicTest{
	
	@Test
	public void sendMailTest(){
		Assert.assertTrue(true);
	}
}
