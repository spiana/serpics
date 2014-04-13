package com.serpics.membership.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;


public class UserSpecification {


    public static Specification<User> isUserInStore(final Store store){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {

                return cb.equal(root.join("stores").as(Store.class), store);
            }
        };
    }


}
