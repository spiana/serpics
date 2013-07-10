package com.serpics.base.persistence.dao;

import java.util.List;

import com.serpics.base.persistence.Locale;



public interface LocaleFactory  {

	public Locale fetchLocaleByLanguage(String locale);
	public Locale fetchLocaleById(String id);
	public Locale create(Locale newLang);
	public List<Locale> fetchAllLocales();
	
}
