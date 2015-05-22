package com.serpics.base.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * The persistent class for the geocode database table.
 * 
 */
@Entity
@Table(name = "geocode")
public class Geocode extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "geocode_id", unique = true, nullable = false)
    private Long geocodeId;

    @Column(nullable = false, length = 100 , name="name" )
    
    @Pattern(regexp="[a-zA-Z0-9]+",message="only letters and numbers allowed !")
    private String code;

    // bi-directional many-to-one association to Country
    @OneToMany(mappedBy = "geocode")
    private Set<Country> countries;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    public Geocode() {
    }

    public Long getGeocodeId() {
        return this.geocodeId;
    }

    public void setGeocodeId(final Long geocodeId) {
        this.geocodeId = geocodeId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Set<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(final Set<Country> countries) {
        this.countries = countries;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }


}