package com.serpics.base.test;

import org.junit.Ignore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
@Transactional
@Ignore
public abstract class BaseTest  extends AbstractTransactionalJunit4SerpicTest{

}
