package com.serpics.base.services;

import com.serpics.base.data.model.Locale;
import com.serpics.core.service.EntityService;



public interface LocaleService extends EntityService<Locale, Long> {

    public Locale findByLanguage(String Language);

}
