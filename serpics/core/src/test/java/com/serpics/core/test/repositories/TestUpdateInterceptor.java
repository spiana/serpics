package com.serpics.core.test.repositories;

import com.serpics.core.data.UpdateInterceptor;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(value=TestUpdateInterceptor.class , order=10)
public class TestUpdateInterceptor implements UpdateInterceptor{

	@Override
	public void beforeSave(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSave(Object entity) {
		// TODO Auto-generated method stub
		
	}

	
	
}
