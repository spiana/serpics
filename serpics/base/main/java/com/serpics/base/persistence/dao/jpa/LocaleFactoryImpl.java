package com.serpics.base.persistence.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.serpics.base.persistence.Locale;
import com.serpics.base.persistence.dao.LocaleFactory;
import com.serpics.core.persistence.dao.jpa.AbstractFactory;

@Repository(value="LanguageFactory")
public class LocaleFactoryImpl extends AbstractFactory implements LocaleFactory {
	
	public Locale create(Locale newLanguage){
		persist(newLanguage);
		
		return newLanguage;
	}

	@Override
	public Locale fetchLocaleByLanguage(String locale) {
		Query q = getEntityManager().createQuery("from Locale l where locale = :locale" );
		q.setParameter("locale", locale);
		return (Locale) q.getSingleResult();
		
	}

	@Override
	public Locale fetchLocaleById(String id) {
		return  (Locale) find(Locale.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locale> fetchAllLocales() {
		Query q = getEntityManager().createQuery("from Locale l" );
		return q.getResultList();
	}





}
