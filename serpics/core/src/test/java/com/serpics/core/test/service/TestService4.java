package com.serpics.core.test.service;

import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.StoreService;

@StoreService(value="testService" , stores={"store2" , "store3"})
public class TestService4 extends AbstractService implements TestService{

	
	public String getServiceClassName() {
		return this.getClass().getSimpleName();
	}

	
}
