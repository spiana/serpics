package com.serpics.base.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the locales database table.
 * 
 */
@Entity
@Table(name = "locales")
public class Locale implements com.serpics.core.persistence.Locale, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locale_id", unique = true, nullable = false)
	private Long localeId;

	@Column(nullable = false, length = 2)
	private String country;

	@Column(nullable = false, length = 2)
	private String locale;

	@Column(length = 40)
	private String name;

	@Column(nullable = false)
	private Timestamp updated;

	public Locale() {
	}

	public Long getLocaleId() {
		return this.localeId;
	}

	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

}