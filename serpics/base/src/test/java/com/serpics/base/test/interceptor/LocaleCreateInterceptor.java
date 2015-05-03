package com.serpics.base.test.interceptor;

import com.serpics.base.data.model.Locale;
import com.serpics.core.data.InterceptorMapping;
import com.serpics.core.data.CreateInterceptor;
import com.serpics.core.data.jpa.AbstractEntity;

public class LocaleCreateInterceptor implements CreateInterceptor<Locale> {

	@Override
	public void beforeCreate(Locale entity) {
			entity.setName("test");
			entity.setCountry("IT");
			entity.setlanguage("it");
		
	}

	@Override
	public void afterCreate(Locale entity) {
		return ;
	}

	
}
