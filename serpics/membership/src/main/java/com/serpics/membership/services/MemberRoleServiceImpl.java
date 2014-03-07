package com.serpics.membership.services;




import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.repositories.MembersRoleRepository;


@Service("memberRoleService")
@Scope("store")
@Transactional(readOnly = true)
public class MemberRoleServiceImpl extends AbstractEntityService<MembersRole, MembersRolePK> implements MemberRoleService {


    @Autowired
    private MembersRoleRepository membersRoleRepository;


    @Override
    public Repository<MembersRole, MembersRolePK> getEntityRepository() {

        return membersRoleRepository;
    }

    @Override
    public Specification<MembersRole> getBaseSpec() {
        return new Specification<MembersRole>() {

            @Override
            public Predicate toPredicate(final Root<MembersRole> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder cb) {

                return cb.isNotNull(root.get("uuid"));
            }

        };
    }

}
