package com.serpics.membership.data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CurrencyLookupPK {

	
	@Column(name="currency_id" , nullable= false)
	private Long currencyId;

	@Column(name="store_id" , nullable= false)
	private Long storeId;
	
	
	public CurrencyLookupPK(Long currencyId, Long storeId) {
		super();
		this.currencyId = currencyId;
		this.storeId = storeId;
	}
	
	public CurrencyLookupPK() {
		super();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currencyId == null) ? 0 : currencyId.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyLookupPK other = (CurrencyLookupPK) obj;
		if (currencyId == null) {
			if (other.currencyId != null)
				return false;
		} else if (!currencyId.equals(other.currencyId))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}
	public Long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	
}
