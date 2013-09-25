package com.serpics.core.test.service;

import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;

@StoreService(value="testService" , stores={"store2" , "store3"} )
public class TestService2 extends AbstractService implements TestService{

	@Override
	public String getServiceClassName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String sayGood() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
