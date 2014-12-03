package com.serpics.membership.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.Membergrouprel;
import com.serpics.membership.repositories.MembergrouprelRepository;

@Service("membergrouprelService")
public class MembergrouprelServiceImpl extends AbstractCommerceEntityService<Membergrouprel, Long> implements
MembergrouprelService {

    @Autowired
    MembergrouprelRepository membergrouprelRepository;

    @Override
    public Repository<Membergrouprel, Long> getEntityRepository() {

        return membergrouprelRepository;
    }

    @Override
    public Specification<Membergrouprel> getBaseSpec() {

        return new Specification<Membergrouprel>() {
            @Override
            public Predicate toPredicate(final Root<Membergrouprel> arg0, final CriteriaQuery<?> arg1,
                    final CriteriaBuilder arg2) {

                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

}
