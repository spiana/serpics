package com.serpics.base.test.interceptor;

import com.serpics.base.data.model.Locale;
import com.serpics.core.data.SaveInterceptor;

public class LocaleCreateInterceptor implements SaveInterceptor<Locale> {

	@Override
	public void beforeSave(Locale entity) {
		if(entity.getName() == null)
			entity.setName("test");
		if (entity.getCountry() == null)
			entity.setCountry("IT");
		if (entity.getLanguage() == null)
			entity.setlanguage("it");
		
	}

	@Override
	public void afterSave(Locale entity) {
		return ;
	}

	
}
