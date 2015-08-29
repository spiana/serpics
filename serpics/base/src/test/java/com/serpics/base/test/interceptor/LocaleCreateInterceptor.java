package com.serpics.base.test.interceptor;

import com.serpics.base.data.model.Locale;
import com.serpics.core.data.CreateInterceptor;

public class LocaleCreateInterceptor implements CreateInterceptor<Locale> {

	@Override
	public void beforeCreate(Locale entity) {
		if(entity.getName() == null)
			entity.setName("test");
		if (entity.getCountry() == null)
			entity.setCountry("IT");
		if (entity.getLanguage() == null)
			entity.setlanguage("it");
		
	}

	@Override
	public void afterCreate(Locale entity) {
		return ;
	}

	
}
