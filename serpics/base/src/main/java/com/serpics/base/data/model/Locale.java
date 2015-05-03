package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;

/**
 * The persistent class for the locales database table.
 * 
 */
@Entity
@Table(name = "locale")
public class Locale extends AbstractEntity implements com.serpics.core.data.model.Locale, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locale_id", unique = true, nullable = false)
    private Long localeId;

    @Column(nullable = false, length = 2)
    private String country;

    @Column(nullable = false, length = 2)
    private String language;

    @Column(length = 40)
    private String name;

    public Locale() {
    }

    @Override
    public Long getLocaleId() {
        return this.localeId;
    }

    public void setLocaleId(final Long localeId) {
        this.localeId = localeId;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    public void setlanguage(final String language) {
        this.language = language;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


}