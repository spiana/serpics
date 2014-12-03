package com.serpics.membership.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.PrimaryAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.repositories.PrimaryAddressRepository;

@Service("primaryAddressService")
@Scope("store")
public class PrimaryAddressServiceImpl extends AbstractCommerceEntityService<PrimaryAddress, Long> implements
PrimaryAddressService {

    protected  static class AddressSpecification{
        protected static Specification<PrimaryAddress> isAddressUser(final User user) {
            return new Specification<PrimaryAddress>() {
                @Override
                public Predicate toPredicate(final Root<PrimaryAddress> root,
                        final CriteriaQuery<?> cq, final CriteriaBuilder cb) {
                    return cb.equal(root.get("member") ,user);
                }
            };

        }
    }

    @Autowired
    PrimaryAddressRepository addressRepository;

    @Override
    public Repository<PrimaryAddress, Long> getEntityRepository() {
        return addressRepository;
    }

    @Override
    public Specification<PrimaryAddress> getBaseSpec() {
        return new Specification<PrimaryAddress>() {
            @Override
            public Predicate toPredicate(final Root<PrimaryAddress> root,
                    final CriteriaQuery<?> arg1, final CriteriaBuilder arg2) {
                return arg2.isNotNull(root.get("uuid"));
            }
        };
    }



}
