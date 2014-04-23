package com.serpics.base.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.core.persistence.jpa.AbstractEntity;

/**
 * The persistent class for the paymethod database table.
 * 
 */
@Entity
@Table(name = "paymethod")
public class Paymethod extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymethod_id", unique = true, nullable = false)
    private Long paymethodId;

    @Column(nullable = false, length = 100)
    private String name;

    // bi-directional many-to-one association to Paymethlookup
    @OneToMany(mappedBy = "paymethod")
    private Set<Paymethlookup> paymethlookups;

    // bi-directional many-to-one association to PaymethodDescr
    // @OneToMany(mappedBy = "paymethod")
    // private Set<PaymethodDescr> paymethodDescrs;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    public Paymethod() {
        super();
    }

    public Paymethod(final String name) {
        super();
        this.name = name;
    }

    public Long getPaymethodId() {
        return this.paymethodId;
    }

    public void setPaymethodId(final Long paymethodId) {
        this.paymethodId = paymethodId;
    }

    public MultilingualString getDescription() {
        return this.description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Paymethlookup> getPaymethlookups() {
        return this.paymethlookups;
    }

    public void setPaymethlookups(final Set<Paymethlookup> paymethlookups) {
        this.paymethlookups = paymethlookups;
    }


}