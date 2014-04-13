package com.serpics.membership.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "stores")
    // private final Set<User> users = new HashSet<User>(0);

    public Store() {
        this.memberType = MemberType.STORE;
    }


    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Store other = (Store) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}