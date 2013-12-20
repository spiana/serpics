package com.serpics.membership.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.persistence.User;
import com.serpics.membership.repositories.MemberGroupRepository;

public class MembergroupServiceImpl extends AbstractEntityService<Membergroup, Long> implements MembergroupService {

    @Autowired
    MemberGroupRepository membergroupRepository;

    @Override
    public Repository<Membergroup, Long> getEntityRepository() {

        return membergroupRepository;
    }

    @Override
    public Specification<Membergroup> getBaseSpec() {

        return new Specification<Membergroup>() {

            @Override
            public Predicate toPredicate(final Root<Membergroup> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {

                return cb.equal(root.get("store"), (User) getCurrentContext().getCustomer());
            }

        };
    }

}
