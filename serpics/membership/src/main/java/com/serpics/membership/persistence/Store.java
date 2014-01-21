package com.serpics.membership.persistence;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.serpics.base.persistence.Currency;
import com.serpics.core.security.StoreRealm;
import com.serpics.membership.MemberType;

/**
 * The persistent class for the stores database table.
 * 
 */
@Entity
@Table(name = "stores")
@DiscriminatorValue("STORE")
public class Store extends Member implements Serializable, StoreRealm {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 250, unique = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    // bi-directional many-to-one association to Membergroup

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<Membergroup> membergroups;

    public Store() {
        this.memberType = MemberType.STORE;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Membergroup> getMembergroups() {
        return this.membergroups;
    }

    public void setMembergroups(final Set<Membergroup> membergroups) {
        this.membergroups = membergroups;
    }

    @Override
    public Set<MembersRole> getMembersRoles() {
        return this.membersRoles;
    }

    @Override
    public void setMembersRoles(final Set<MembersRole> membersRoles) {
        this.membersRoles = membersRoles;
    }

    @PrePersist
    @PreUpdate
    public void preUpdated() {
        setUpdated(new Timestamp(new Date().getTime()));
    }

    // @Override
    // public String getRealm() {
    // return getName();
    // }

    @Override
    public Long getStoreId() {
        return getMemberId();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

}