package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the geocode_descr database table.
 * 
 */
@Entity
@Table(name="geocode_descr" )
public class GeocodeDescr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GeocodeDescrPK id;

	@Column(nullable=false, length=1000)
	private String description;

	//bi-directional many-to-one association to Geocode
    @ManyToOne
	@JoinColumn(name="geocode_id", nullable=false, insertable=false, updatable=false)
	private Geocode geocode;

	//bi-directional many-to-one association to Locale
    @ManyToOne
	@JoinColumn(name="locale_id", nullable=false, insertable=false, updatable=false)
	private Locale locale;

    public GeocodeDescr() {
    }

	public GeocodeDescrPK getId() {
		return this.id;
	}

	public void setId(GeocodeDescrPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Geocode getGeocode() {
		return this.geocode;
	}

	public void setGeocode(Geocode geocode) {
		this.geocode = geocode;
	}
	
	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}