package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the paymethod_descr database table.
 * 
 */
@Embeddable
public class PaymethodDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="paymethod_id", unique=true, nullable=false)
	private Long paymethodId;

	@Column(name="locale_id", unique=true, nullable=false)
	private Long localeId;

    public PaymethodDescrPK() {
    }
	public Long getPaymethodId() {
		return this.paymethodId;
	}
	public void setPaymethodId(Long paymethodId) {
		this.paymethodId = paymethodId;
	}
	public Long getLocaleId() {
		return this.localeId;
	}
	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PaymethodDescrPK)) {
			return false;
		}
		PaymethodDescrPK castOther = (PaymethodDescrPK)other;
		return 
			this.paymethodId.equals(castOther.paymethodId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.paymethodId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}