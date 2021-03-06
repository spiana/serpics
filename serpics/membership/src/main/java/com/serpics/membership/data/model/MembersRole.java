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
package com.serpics.membership.data.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.data.model.Store;


/**
 * The persistent class for the members_role database table.
 * 
 */
@Entity
@Table(name="members_role" )
public class MembersRole  extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MembersRolePK id;

    //bi-directional many-to-one association to Member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable=false, insertable=false, updatable=false)
    private Member member;

    //bi-directional many-to-one association to Role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
    @NotNull(message = "{membersRole.role.notnull}")
    private Role role;

    //bi-directional many-to-one association to Store
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", nullable=false, insertable=false, updatable=false)
    private Store store;

    public MembersRole() {
    }

    public MembersRole(final Member member, final Role role, final Store store) {
        super();
        this.member = member;
        this.role = role;
        this.store = store;
        this.id= new MembersRolePK(role.getId() ,member.getId() , store.getId());

    }

    public MembersRolePK getId() {
        return this.id;
    }

    public void setId(final MembersRolePK id) {
        this.id = id;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

}