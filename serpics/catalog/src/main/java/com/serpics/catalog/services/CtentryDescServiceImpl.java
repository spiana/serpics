package com.serpics.catalog.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.catalog.persistence.CtentryDescr;
import com.serpics.catalog.repositories.CtentryDescrRepository;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;

@Service("ctentryDescrService")
@Scope("store")
public class CtentryDescServiceImpl extends AbstractEntityService<CtentryDescr, Long> implements CtentryDescrService {

    @Autowired
    CtentryDescrRepository ctentryDescrRepository;

    @Override
    public Repository<CtentryDescr, Long> getEntityRepository() {

        return ctentryDescrRepository;
    }

    @Override
    public Specification<CtentryDescr> getBaseSpec() {
        return new Specification<CtentryDescr>() {
            @Override
            public Predicate toPredicate(final Root<CtentryDescr> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {
                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

}
