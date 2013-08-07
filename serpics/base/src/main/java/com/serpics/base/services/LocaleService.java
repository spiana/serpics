package com.serpics.base.services;

import java.util.List;

import com.serpics.base.persistence.Locale;



public interface LocaleService {
	
	public Locale fetchLocaleByLanguage(String locale);
	public Locale fetchLocaleById(String id);
	public Locale create(Locale newLocale);
	public List<Locale> fetchAllLocales();

}
