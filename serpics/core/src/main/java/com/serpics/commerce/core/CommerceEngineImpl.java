package com.serpics.commerce.core;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.AbstractEngine;
import com.serpics.core.SerpicsException;

public class CommerceEngineImpl  extends AbstractEngine<CommerceSessionContext> implements CommerceEngine{

	@Override
	public CommerceSessionContext connect(String storeName) throws SerpicsException {
		CommerceSessionContext context =  super.connect(storeName);
		context.setCurrency(context.getStoreRealm().getCurrency());
		
		return context;
	}
}
