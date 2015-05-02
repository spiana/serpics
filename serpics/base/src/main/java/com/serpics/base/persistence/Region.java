package com.serpics.base.persistence;

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

/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name = "regions")
public class Region extends com.serpics.core.persistence.jpa.AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "regions_id", unique = true, nullable = false)
    private Long regionsId;

    @Column(nullable = false, length = 100)
    private String name;

    // bi-directional many-to-one association to Country
    @ManyToOne
    @JoinColumn(name = "countries_id", nullable = false)
    private Country country;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description = new MultilingualString();

    public Region() {
    }

    public Long getRegionsId() {
        return this.regionsId;
    }

    public void setRegionsId(final Long regionsId) {
        this.regionsId = regionsId;
    }

    public MultilingualString getDescription() {
        return this.description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }



}