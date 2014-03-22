package com.serpics.base.services;

import com.serpics.base.persistence.Locale;
import com.serpics.core.service.EntityService;



public interface LocaleService extends EntityService<Locale, Long> {

    public Locale findByLanguage(String Language);

}
