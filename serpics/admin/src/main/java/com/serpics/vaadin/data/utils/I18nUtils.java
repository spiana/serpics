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
