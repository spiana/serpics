package com.serpics.base.data.interceptor;

import javax.annotation.Resource;

import com.serpics.base.data.model.BaseAttribute;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;

public class BaseAttributeSaveInterceptor implements SaveInterceptor<BaseAttribute> {

	@Resource
	CommerceEngine engine;
	
	@Override
	public void beforeSave(BaseAttribute entity) {
		entity.setStoreId(engine.getCurrentContext().getStoreId());
		
	}

	@Override
	public void afterSave(BaseAttribute entity) {
	
		
	}

}
