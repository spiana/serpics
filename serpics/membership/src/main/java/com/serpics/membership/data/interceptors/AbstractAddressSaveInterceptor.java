package com.serpics.membership.data.interceptors;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.SaveInterceptor;
import com.serpics.membership.data.model.AbstractAddress;
import com.serpics.membership.data.model.Member;

public class AbstractAddressSaveInterceptor implements SaveInterceptor<AbstractAddress> {

	@Autowired
	CommerceEngine engine ;
	
	@Override
	public void beforeSave(AbstractAddress entity) {
		if (entity.getMember() == null)
			entity.setMember((Member) engine.getCurrentContext().getCustomer());
		
	}
	@Override
	public void afterSave(AbstractAddress entity) {
		
	}
	
	

}
