package com.serpics.membership.data.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.base.data.model.Currency;
import com.serpics.core.data.jpa.AbstractEntity;

//@Entity
public class Currencylookup extends AbstractEntity{

	private static final long serialVersionUID = 5123294972931944960L;

	@Id
	CurrencyLookupPK id;
	
	 @ManyToOne
	@JoinColumn(name="store_id", insertable=false, updatable=false)
	Store store;
	
	@ManyToOne
	@JoinColumn(name="currency_id", insertable=false, updatable=false)
	Currency currency;

	public CurrencyLookupPK getId() {
		return id;
	}

	public void setId(CurrencyLookupPK id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
