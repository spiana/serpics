package com.serpics.membership.persistence;

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
public class Membergrouprel extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MembgrouprelPK id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Member2GroupRelType status;

    @NotNull
    @Temporal( TemporalType.DATE)
    @Column(name="valid_from", nullable=false)
    private Date validFrom;

    @NotNull
    @Temporal( TemporalType.DATE)
    @Column(name="valid_to", nullable=false)
    private Date validTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membergroups_id", nullable = false, insertable = false, updatable = false)
    private Membergroup membergroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
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