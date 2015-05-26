package com.serpics.base.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.base.data.model.Locale;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;

@Service("localeService")
@Scope("store")
public class LocaleServiceImpl extends AbstractCommerceEntityService<Locale, Long> implements LocaleService {

    @Autowired
    LocaleRepository localeRepository;

    @Override
    public Repository<Locale, Long> getEntityRepository() {
        return localeRepository;
    }

    @Override
    public Specification<Locale> getBaseSpec() {

        return new Specification<Locale>() {
            @Override
            public Predicate toPredicate(final Root<Locale> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {
                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

    @Override
    public Locale findByLanguage(final String language) {
        return localeRepository.findByLanguage(language);
    }

}
