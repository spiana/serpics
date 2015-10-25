package com.serpics.test;

//import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration({ "classpath:META-INF/applicationContext.xml", "classpath:META-INF/core-serpics.xml" })
@TestExecutionListeners({ ExecutionTestListener.class})
@TransactionConfiguration(defaultRollback = true )
//@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTransactionalJunit4SerpicTest extends AbstractTransactionalJUnit4SpringContextTests{

}
