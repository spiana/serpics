package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pricelist_descr database table.
 * 
 */
@Embeddable
public class PricelistDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="pricelist_id")
	private Long pricelistId;

	@Column(name="locale_id")
	private Long localeId;

    public PricelistDescrPK() {
    }
	public Long getPricelistId() {
		return this.pricelistId;
	}
	public void setPricelistId(Long pricelistId) {
		this.pricelistId = pricelistId;
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
		if (!(other instanceof PricelistDescrPK)) {
			return false;
		}
		PricelistDescrPK castOther = (PricelistDescrPK)other;
		return 
			this.pricelistId.equals(castOther.pricelistId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pricelistId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}