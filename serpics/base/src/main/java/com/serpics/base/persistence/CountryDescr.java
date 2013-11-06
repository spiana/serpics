package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the country_descr database table.
 * 
 */
@Entity
@Table(name="country_descr" )
public class CountryDescr extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountryDescrPK id;

	@Column(nullable=false, length=1000)
	private String description;

	//bi-directional many-to-one association to Country
    @ManyToOne
	@JoinColumn(name="countries_id", nullable=false, insertable=false, updatable=false)
	private Country country;

	//bi-directional many-to-one association to Locale
    @ManyToOne
	@JoinColumn(name="locale_id", nullable=false, insertable=false, updatable=false)
	private Locale locale;

    public CountryDescr() {
    }

	public CountryDescrPK getId() {
		return this.id;
	}

	public void setId(CountryDescrPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}