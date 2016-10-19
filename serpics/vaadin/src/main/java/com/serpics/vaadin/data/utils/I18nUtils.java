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
package com.serpics.vaadin.data.utils;

import com.serpics.core.EngineFactory;
import com.vaadin.ui.UI;

public class I18nUtils {

	public static String getMessage(String code , String defaultValue){
		return EngineFactory.getCurrentApplicationContext().getMessage(code.toLowerCase() ,null,defaultValue,UI.getCurrent().getSession().getLocale());
	}
	
	public static String getMessage(String code , Object[] values , String defaultValue){
		return EngineFactory.getCurrentApplicationContext().getMessage(code.toLowerCase() ,values,defaultValue,UI.getCurrent().getSession().getLocale());
	}
}
