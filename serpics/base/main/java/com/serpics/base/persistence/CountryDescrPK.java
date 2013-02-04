package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the country_descr database table.
 * 
 */
@Embeddable
public class CountryDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="countries_id", unique=true, nullable=false)
	private Long countriesId;

	@Column(name="locale_id", unique=true, nullable=false)
	private Long localeId;

    public CountryDescrPK() {
    }
	public Long getCountriesId() {
		return this.countriesId;
	}
	public void setCountriesId(Long countriesId) {
		this.countriesId = countriesId;
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
		if (!(other instanceof CountryDescrPK)) {
			return false;
		}
		CountryDescrPK castOther = (CountryDescrPK)other;
		return 
			this.countriesId.equals(castOther.countriesId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.countriesId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}