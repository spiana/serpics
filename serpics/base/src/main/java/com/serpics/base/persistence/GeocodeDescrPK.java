package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the geocode_descr database table.
 * 
 */
@Embeddable
public class GeocodeDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="geocode_id", unique=true, nullable=false)
	private Long geocodeId;

	@Column(name="locale_id", unique=true, nullable=false)
	private Long localeId;

    public GeocodeDescrPK() {
    }
	public Long getGeocodeId() {
		return this.geocodeId;
	}
	public void setGeocodeId(Long geocodeId) {
		this.geocodeId = geocodeId;
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
		if (!(other instanceof GeocodeDescrPK)) {
			return false;
		}
		GeocodeDescrPK castOther = (GeocodeDescrPK)other;
		return 
			this.geocodeId.equals(castOther.geocodeId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.geocodeId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}