package com.serpics.base.repositories;

import com.serpics.base.persistence.Locale;
import com.serpics.core.data.Repository;

public interface LocaleRepository extends Repository<Locale, Long> {

    public Locale findByLanguage(String Language);
}
