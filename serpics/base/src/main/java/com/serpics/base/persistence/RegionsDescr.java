package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regions_descr database table.
 * 
 */
@Entity
@Table(name="regions_descr" )
public class RegionsDescr extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RegionsDescrPK id;

	@Column(nullable=false, length=1000)
	private String description;

	//bi-directional many-to-one association to Region
    @ManyToOne
	@JoinColumn(name="regions_id", nullable=false, insertable=false, updatable=false)
	private Region region;

	//bi-directional many-to-one association to Locale
    @ManyToOne
	@JoinColumn(name="locale_id", nullable=false, insertable=false, updatable=false)
	private Locale locale;

    public RegionsDescr() {
    }

	public RegionsDescrPK getId() {
		return this.id;
	}

	public void setId(RegionsDescrPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}