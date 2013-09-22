package com.serpics.core.test.service;

import com.serpics.core.service.AbstractService;
import com.serpics.stereotype.Service;

@Service(value="testService" , store = {"store1"})
public class TestService1 extends AbstractService implements TestService{

	@Override
	public String getServiceClassName() {
		return this.getClass().getSimpleName();
	}

}
