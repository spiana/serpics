package com.serpics.base.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name = "currency")
public class Currency extends com.serpics.core.persistence.jpa.Entity implements com.serpics.core.persistence.Currency,
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_id", unique = true, nullable = false)
	private Long currencyId;

	@Column(length = 1000)
	private String descriprion;

	@Column(name = "iso_code", nullable = false, length = 3)
	private String isoCode;

	public Currency() {
	}

	public Long getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getDescriprion() {
		return this.descriprion;
	}

	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}

	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

}