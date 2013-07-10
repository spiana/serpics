package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the paymethlookup database table.
 * 
 */
@Embeddable
public class PaymethlookupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="paymethod_id", unique=true, nullable=false)
	private Long paymethodId;

	@Column(name="store_id", unique=true, nullable=false)
	private Long storeId;

    public PaymethlookupPK() {
    }
	public Long getPaymethodId() {
		return this.paymethodId;
	}
	public void setPaymethodId(Long paymethodId) {
		this.paymethodId = paymethodId;
	}
	public Long getStoreId() {
		return this.storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PaymethlookupPK)) {
			return false;
		}
		PaymethlookupPK castOther = (PaymethlookupPK)other;
		return 
			this.paymethodId.equals(castOther.paymethodId)
			&& this.storeId.equals(castOther.storeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.paymethodId.hashCode();
		hash = hash * prime + this.storeId.hashCode();
		
		return hash;
    }
}