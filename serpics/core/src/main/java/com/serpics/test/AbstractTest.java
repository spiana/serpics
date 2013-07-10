package com.serpics.test;

import javax.annotation.Resource;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.serpics.base.services.BaseService;
import com.serpics.core.SerpicsException;
import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.stereotype.SerpicsTest;

@TestExecutionListeners({ ExecutionTestListener.class, DependencyInjectionTestExecutionListener.class })
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
