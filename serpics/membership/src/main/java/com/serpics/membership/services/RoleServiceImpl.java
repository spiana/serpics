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
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.repositories.RoleRepository;

@Service("roleService")
@Scope("store")
public class RoleServiceImpl extends AbstractCommerceEntityService<Role, Long> implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Repository<Role, Long> getEntityRepository() {
        return roleRepository;
    }

    @Override
    public Specification<Role> getBaseSpec() {

        return new Specification<Role>() {
            @Override
            public Predicate toPredicate(final Root<Role> arg0, final CriteriaQuery<?> arg1, final CriteriaBuilder arg2) {
                return arg2.isNotNull(arg0.get("uuid"));
            }
        };
    }

}
