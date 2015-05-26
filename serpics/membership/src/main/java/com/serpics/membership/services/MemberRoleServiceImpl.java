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

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.MembersRolePK;
import com.serpics.membership.data.repositories.MembersRoleRepository;


@Service("memberRoleService")
@Scope("store")
@Transactional(readOnly = true)
public class MemberRoleServiceImpl extends AbstractCommerceEntityService<MembersRole, MembersRolePK> implements MemberRoleService {


    @Autowired
    private MembersRoleRepository membersRoleRepository;


    @Override
    public Repository<MembersRole, MembersRolePK> getEntityRepository() {

        return membersRoleRepository;
    }

    @Override
    public MembersRole create(final MembersRole entity) {
        if (entity.getId() == null && entity.getRole() != null && entity.getMember() != null) {
            final MembersRolePK pk = new MembersRolePK(entity.getRole().getRoleId(),
                    entity.getMember().getMemberId(), getCurrentContext().getStoreId());
            entity.setId(pk);
        }

        return super.create(entity);
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
