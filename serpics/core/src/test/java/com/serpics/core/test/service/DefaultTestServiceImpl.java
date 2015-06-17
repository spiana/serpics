package com.serpics.core.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.service.AbstractService;
import com.serpics.core.test.hook.TestHook;

//@StoreService(value="testService"  )
public class DefaultTestServiceImpl extends AbstractService implements TestService {

	@Autowired
	TestHook testHook ;
	
	@Override
	public String getServiceClassName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String sayGood() {
		
		return testHook.sayGood();
	}

}
