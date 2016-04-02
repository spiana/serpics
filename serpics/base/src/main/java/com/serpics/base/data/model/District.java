package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name = "district")
public class District extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "district_id", unique = true, nullable = false)
    private Long Id;

    @Column( length = 20 , unique=true , nullable= false)
    @NotNull(message = "{district.isoCode.notnull}")
    @Size(max = 20 , message = "{district.isoCode.size}")
    private String isoCode;
    
    // bi-directional many-to-one association to Country
    @ManyToOne
    @JoinColumn(name = "countries_id", nullable = false)
    private Country country;

    // bi-directional many-to-one association to Country
    @ManyToOne
    @JoinColumn(name = "regions_id", nullable = true)
    private Region region;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description = new MultilingualString();

    public District() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(final Long Id) {
        this.Id =Id;
    }

   
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

	
	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public MultilingualString getDescription() {
		return description;
	}

	public void setDescription(MultilingualString description) {
		this.description = description;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}



}