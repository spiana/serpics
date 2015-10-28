package com.serpics.core.test.service;



import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.test.hook.TestHook;
import com.serpics.stereotype.StoreService;

@StoreService(value="testService", stores={"store4"} )
public class TestService1Impl extends TestServiceImpl1 implements MyTestService{
	
	@Autowired
	TestHook testHook;

	
	@Override
	public String sayHello() {
		
		return "hello";
	}


	@Override
	public String sayGood() {
		
		return testHook.sayGood();
	}

	
}
