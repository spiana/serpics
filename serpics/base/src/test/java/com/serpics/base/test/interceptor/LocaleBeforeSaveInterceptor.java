package com.serpics.base.test.interceptor;

import com.serpics.base.persistence.Locale;
import com.serpics.core.data.AbstractInterceptor;
import com.serpics.core.data.BeforeCreateInterceptor;
import com.serpics.core.persistence.jpa.AbstractEntity;

public class LocaleBeforeSaveInterceptor extends AbstractInterceptor implements BeforeCreateInterceptor<Locale> {

	@Override
	public void beforeCreate(Locale entity) {
			entity.setName("test");
			entity.setCountry("IT");
			entity.setlanguage("it");
		
	}

	

}
