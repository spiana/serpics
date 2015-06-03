package com.serpics.base.test;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.test.AbstractTest;

@ContextConfiguration({ "classpath*:META-INF/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class BaseTest {

}
