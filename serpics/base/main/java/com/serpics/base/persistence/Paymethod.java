package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the paymethod database table.
 * 
 */
@Entity
@Table(name="paymethod")
public class Paymethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paymethod_id", unique=true, nullable=false)
	private Long paymethodId;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=100)
	private String name;

	//bi-directional many-to-one association to Paymethlookup
	@OneToMany(mappedBy="paymethod")
	private Set<Paymethlookup> paymethlookups;

	//bi-directional many-to-one association to PaymethodDescr
	@OneToMany(mappedBy="paymethod")
	private Set<PaymethodDescr> paymethodDescrs;

    public Paymethod() {
    }

	public Long getPaymethodId() {
		return this.paymethodId;
	}

	public void setPaymethodId(Long paymethodId) {
		this.paymethodId = paymethodId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Paymethlookup> getPaymethlookups() {
		return this.paymethlookups;
	}

	public void setPaymethlookups(Set<Paymethlookup> paymethlookups) {
		this.paymethlookups = paymethlookups;
	}
	
	public Set<PaymethodDescr> getPaymethodDescrs() {
		return this.paymethodDescrs;
	}

	public void setPaymethodDescrs(Set<PaymethodDescr> paymethodDescrs) {
		this.paymethodDescrs = paymethodDescrs;
	}
	
}