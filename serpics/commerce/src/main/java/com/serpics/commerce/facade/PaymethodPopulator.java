package com.serpics.commerce.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.data.model.Paymethod;
import com.serpics.commerce.facade.data.PaymethodData;
import com.serpics.core.facade.Populator;

public class PaymethodPopulator implements Populator<Paymethod, PaymethodData>{
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override 
	public void populate(Paymethod source, PaymethodData target) {		
	
		target.setPaymethodId(source.getPaymethodId());
		target.setCreated(source.getCreated());
		target.setDescription(source.getDescription().getText(commerceEngine.getCurrentContext().getLocale().getLanguage()));
		target.setName(source.getName());
		
		target.setUuid(source.getUuid());
		target.setUpdated(source.getUpdated());
		
	}

	
}
