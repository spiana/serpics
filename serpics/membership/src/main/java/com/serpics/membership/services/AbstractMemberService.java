/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.membership.services;

import java.io.Serializable;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.base.data.model.Store;
import com.serpics.base.data.repositories.StoreRepository;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.membership.data.model.Member;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.MembersRolePK;
import com.serpics.membership.data.model.PermanentAddress;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.repositories.PermanentAddressRepository;

public abstract class AbstractMemberService<T extends Member, ID extends Serializable> extends AbstractEntityService<T, ID, CommerceSessionContext> implements MemberService<T, ID> {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PermanentAddressRepository addressRepository;
   
    protected T adjustMemberRoles(final T member) {
        if (member.getMembersRoles() == null) {
            member.setMembersRoles(new HashSet<MembersRole>(0));
        }

        final Store s = storeRepository.findOne(((Store) getCurrentContext().getStoreRealm()).getId());

        for (final MembersRole memberRole : member.getMembersRoles()) {
            if (memberRole.getId() == null) {
                if (memberRole.getStore() == null)
                    memberRole.setStore(s);

                memberRole.setMember(member);
                final MembersRolePK pk = new MembersRolePK();
                pk.setStoresStoreId(s.getId());
                pk.setMemberId(member.getId());
                pk.setRoleId(memberRole.getRole().getId());
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
