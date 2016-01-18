package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the paymethlookup database table.
 * 
 */
@Embeddable
public class TaxlookupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="taxes_id",  nullable=false)
	private Long taxesId;

	@Column(name="store_id",  nullable=false)
	private Long storeId;

    public TaxlookupPK() {
    }
	
	public Long getStoreId() {
		return this.storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getTaxesId() {
		return taxesId;
	}

	public void setTaxesId(Long taxesId) {
		this.taxesId = taxesId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((taxesId == null) ? 0 : taxesId.hashCode());
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
		TaxlookupPK other = (TaxlookupPK) obj;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (taxesId == null) {
			if (other.taxesId != null)
				return false;
		} else if (!taxesId.equals(other.taxesId))
			return false;
		return true;
	}

	
}