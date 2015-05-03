package com.serpics.commerce.data.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the shipmode_descr database table.
 * 
 */
@Embeddable
public class ShipmodeDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="shipmode_id", unique=true, nullable=false)
	private Long shipmodeId;

	@Column(name="locale_id", unique=true, nullable=false)
	private Long localeId;

    public ShipmodeDescrPK() {
    }
	public Long getShipmodeId() {
		return this.shipmodeId;
	}
	public void setShipmodeId(Long shipmodeId) {
		this.shipmodeId = shipmodeId;
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
		if (!(other instanceof ShipmodeDescrPK)) {
			return false;
		}
		ShipmodeDescrPK castOther = (ShipmodeDescrPK)other;
		return 
			this.shipmodeId.equals(castOther.shipmodeId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.shipmodeId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}