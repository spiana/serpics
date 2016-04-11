package com.serpics.scheduler.interceptor;

import javax.annotation.Resource;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.scheduler.model.SystemJobDetails;

public class SystemJobDetailsSaveInterceptor implements SaveInterceptor<SystemJobDetails> {

	@Resource
	CommerceEngine engine;
	
	@Override
	public void beforeSave(SystemJobDetails entity) {
		entity.setStore(null);
		
	}

	@Override
	public void afterSave(SystemJobDetails entity) {
		
	}

}
