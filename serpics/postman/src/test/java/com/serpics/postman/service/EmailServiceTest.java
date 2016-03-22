package com.serpics.postman.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.serpics.test.AbstractTransactionalJunit4SerpicTest;
import com.serpics.test.ExecutionTestListener;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml", "classpath:META-INF/membership-serpics.xml",
	"classpath:META-INF/catalog-serpics.xml", 
	"classpath:META-INF/postman-serpics.xml" })
@TestExecutionListeners({ ExecutionTestListener.class})
@TransactionConfiguration(defaultRollback = true )
@Ignore
public class EmailServiceTest extends AbstractTransactionalJunit4SerpicTest{
	
	@Test
	public void sendMailTest(){
		
	}
}
