package com.serpics.membership.data.repositories;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.serpics.membership.UserType;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.stereotype.DefaultSpec;


@DefaultSpec(User.class)
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
    

    public static Specification<User> searchByName(final String name){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	Expression<String> e = root.get("email");
            	Predicate nameLike = cb.like(e, "%" +name +"%");
                return nameLike;
            }
        };
    }
    
    public static Specification<User> findByUserType(final UserType userType){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(final Root<User> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {
            	return cb.equal(root.get("userType"), userType);
            }
        };
    }

}
