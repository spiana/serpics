package com.serpics.test;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.services.BaseService;
import com.serpics.core.SerpicsException;
import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.stereotype.SerpicsTest;

@ContextConfiguration({ "classpath:resources/applicationContext.xml" })
@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTest {

	@Resource
	protected BaseService baseService;

	public void init() throws SerpicsException {
		baseService.initIstance();
		SerpicsTest s = this.getClass().getAnnotation(SerpicsTest.class);
		// reset test scope store after Test platform initialization
		if (s != null)
			StoreScopeContextHolder.setCurrentStoreRealm(s.value());
	}
}
