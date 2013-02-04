package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the regions_descr database table.
 * 
 */
@Embeddable
public class RegionsDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="regions_id", unique=true, nullable=false)
	private Long regionsId;

	@Column(name="locale_id", unique=true, nullable=false)
	private Long localeId;

    public RegionsDescrPK() {
    }
	public Long getRegionsId() {
		return this.regionsId;
	}
	public void setRegionsId(Long regionsId) {
		this.regionsId = regionsId;
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
		if (!(other instanceof RegionsDescrPK)) {
			return false;
		}
		RegionsDescrPK castOther = (RegionsDescrPK)other;
		return 
			this.regionsId.equals(castOther.regionsId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.regionsId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}