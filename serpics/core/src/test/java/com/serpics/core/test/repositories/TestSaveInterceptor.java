package com.serpics.core.test.repositories;

import com.serpics.core.data.SaveInterceptor;
import com.serpics.stereotype.ModelInterceptor;

@ModelInterceptor(value=TestSaveInterceptor.class , order=10)
public class TestSaveInterceptor implements SaveInterceptor{

	@Override
	public void beforeSave(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSave(Object entity) {
		// TODO Auto-generated method stub
		
	}

	
	
}
