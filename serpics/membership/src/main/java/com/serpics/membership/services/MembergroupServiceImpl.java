package com.serpics.membership.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.persistence.Membergroup;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.repositories.MemberGroupRepository;

@Service("membergroupService")
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

                return cb.equal(root.get("store"), (Store) getCurrentContext().getStoreRealm());
            }

        };
    }

    @Override
    public Membergroup create(final Membergroup entity) {
        entity.setStore((Store) getCurrentContext().getStoreRealm());
        return super.create(entity);
    }
}
