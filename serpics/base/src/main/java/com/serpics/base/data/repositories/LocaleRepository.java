package com.serpics.base.data.repositories;

import com.serpics.base.data.model.Locale;
import com.serpics.core.data.Repository;

public interface LocaleRepository extends Repository<Locale, Long>  {

    public Locale findByLanguage(String Language);
}

