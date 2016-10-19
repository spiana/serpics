/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
