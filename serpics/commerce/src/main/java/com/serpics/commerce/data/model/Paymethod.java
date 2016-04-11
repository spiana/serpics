package com.serpics.commerce.data.model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.core.data.jpa.AbstractEntity;

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

    @Size(max = 100, message ="{paymethod.name.size}")
    @NotNull(message="{paymethod.name.notnull}")
    @Column(nullable = false, length = 100, unique = true)
    private String name;

    // bi-directional many-to-one association to Paymethlookup
    @OneToMany(mappedBy = "paymethod")
    private Set<Paymethodlookup> paymethlookups;

    // bi-directional many-to-one association to PaymethodDescr
    // @OneToMany(mappedBy = "paymethod")
    // private Set<PaymethodDescr> paymethodDescrs;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    private String paymentStrategy;
    
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

    public Set<Paymethodlookup> getPaymethlookups() {
        return this.paymethlookups;
    }

    public void setPaymethlookups(final Set<Paymethodlookup> paymethlookups) {
        this.paymethlookups = paymethlookups;
    }

	public String getPaymentStrategy() {
		return paymentStrategy;
	}

	public void setPaymentStrategy(String paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}


}