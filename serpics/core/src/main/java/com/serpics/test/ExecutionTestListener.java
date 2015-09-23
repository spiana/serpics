package com.serpics.test;


import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.serpics.core.scope.StoreScopeContextHolder;
import com.serpics.stereotype.SerpicsTest;

public class ExecutionTestListener extends AbstractTestExecutionListener {
	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		super.prepareTestInstance(testContext);
		//Thread.currentThread().setContextClassLoader(testContext.getApplicationContext().getClassLoader());
		SerpicsTest s = testContext.getTestClass().getAnnotation(SerpicsTest.class);
		if (s != null)
			StoreScopeContextHolder.setCurrentStoreRealm(s.value());

	}

}
