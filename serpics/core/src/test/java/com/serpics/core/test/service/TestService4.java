package com.serpics.core.test.service;

import com.serpics.core.service.AbstractService;

//@StoreService(value="testService" )
public class TestService4 extends AbstractService implements TestService{

	
	public String getServiceClassName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String sayGood() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
