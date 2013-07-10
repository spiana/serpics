package com.serpics.base.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.persistence.Locale;
import com.serpics.base.persistence.dao.jpa.LocaleFactoryImpl;
import com.serpics.core.service.AbstractService;

public class LanguageServiceImpl extends AbstractService implements LanguageService {
	@Autowired(required=true)
	LocaleFactoryImpl localeFactory;

	public LocaleFactoryImpl getLanguageFactory() {
		return localeFactory;
	}

	public void setLanguageFactory(LocaleFactoryImpl languageFactory) {
		this.localeFactory = languageFactory;
	}

	@Override
	@Transactional(readOnly=true , propagation=Propagation.SUPPORTS)
	public Locale fetchLocaleByLanguage(String locale) {
		
		return localeFactory.fetchLocaleByLanguage(locale);
	}

	@Override
	@Transactional(readOnly=true , propagation=Propagation.SUPPORTS)
	public Locale fetchLocaleById(String id) {
		return localeFactory.fetchLocaleById(id);
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.SUPPORTS)
	public Locale create(Locale newLocale) {
		return localeFactory.create(newLocale);
	}

	@Override
	@Transactional(readOnly=true , propagation=Propagation.SUPPORTS)
	public List<Locale> fetchAllLocales() {
		return localeFactory.fetchAllLocales();
	}
	
	

}
