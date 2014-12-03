package com.serpics.membership.services;

import java.io.Serializable;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.service.AbstractCommerceEntityService;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.session.SessionContext;
import com.serpics.membership.persistence.Member;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.repositories.PermanentAddressRepository;
import com.serpics.membership.repositories.StoreRepository;

public abstract class AbstractMemberService<T extends Member, ID extends Serializable> extends AbstractCommerceEntityService<T, ID>
implements MemberService<T, ID> {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PermanentAddressRepository addressRepository;

    protected T adjustMemberRoles(final T member) {
        if (member.getMembersRoles() == null) {
            member.setMembersRoles(new HashSet<MembersRole>(0));
        }

        final Store s = storeRepository.findOne(((Store) getCurrentContext().getStoreRealm()).getStoreId());

        for (final MembersRole memberRole : member.getMembersRoles()) {
            if (memberRole.getId() == null) {
                if (memberRole.getStore() == null)
                    memberRole.setStore(s);

                memberRole.setMember(member);
                final MembersRolePK pk = new MembersRolePK();
                pk.setStoresStoreId(s.getStoreId());
                pk.setMemberId(member.getMemberId());
                pk.setRoleId(memberRole.getRole().getRoleId());
                memberRole.setId(pk);
            }
        }

        return member;
    }

    protected T adjustAddresses(final T member) {

        // if (member.getPermanentAddresses() != null)
        // for (final PermanentAddress address : member.getPermanentAddresses()) {
        // if (address.getMember() == null)
        // address.setMember(member);
        // }

        if (member.getPrimaryAddress() != null && member.getPrimaryAddress().getMember() == null)
            member.getPrimaryAddress().setMember(member);

        return member;

    }

    @Override
    @Transactional
    public T addAddress(final PermanentAddress address, final T member) {
        Assert.notNull(member);
        Assert.notNull(address);
        address.setMember(member);
        member.getPermanentAddresses().add(address);
        addressRepository.saveAndFlush(address);
        return getEntityRepository().saveAndFlush(member);

    }

    @Override
    @Transactional
    public T addAddress(final PermanentAddress address, final ID userId) {
        final T member = getEntityRepository().findOne(userId);
        return addAddress(address, member);
    }

    @Override
    public T addRole(final Role role, final T member) {
        final MembersRole membersRole = new MembersRole(member, role, (Store) getCurrentContext().getStoreRealm());
        member.getMembersRoles().add(membersRole);
        return getEntityRepository().saveAndFlush(member);
    }

}
