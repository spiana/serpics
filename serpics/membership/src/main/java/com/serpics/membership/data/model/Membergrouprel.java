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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.serpics.membership.Member2GroupRelType;

/**
 * The persistent class for the membgrouprel database table.
 * 
 */
@Entity
@Table(name="membgrouprel")
public class Membergrouprel extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MembgrouprelPK id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    @NotNull( message = "{membgrouprel.status.notnull}")
    private Member2GroupRelType status;

    @NotNull(message = "{membgrouprel.validFrom.notnull}")
    @Temporal( TemporalType.DATE)
    @Column(name="valid_from", nullable=false)
    private Date validFrom;

    @NotNull(message = "{membgrouprel.validTo.notnull}")
    @Temporal( TemporalType.DATE)
    @Column(name="valid_to", nullable=false)
    private Date validTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membergroups_id", nullable = false, insertable = false, updatable = false)
    @NotNull(message="{membgrouprel.membergroup.notnull}")
    private Membergroup membergroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
    @NotNull(message="{membgrouprel.member.notnull}")
    private Member member;

    public Membergrouprel() {
    }

    public MembgrouprelPK getId() {
        return this.id;
    }

    public void setId(final MembgrouprelPK id) {
        this.id = id;
    }

    public Member2GroupRelType getStatus() {
        return this.status;
    }

    public void setStatus(final Member2GroupRelType status) {
        this.status = status;
    }

    public Date getValidFrom() {
        return this.validFrom;
    }

    public void setValidFrom(final Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return this.validTo;
    }

    public void setValidTo(final Date validTo) {
        this.validTo = validTo;
    }

    public Membergroup getMembergroup() {
        return membergroup;
    }

    public void setMembergroup(final Membergroup membergroup) {
        this.membergroup = membergroup;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

}