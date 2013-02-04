package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name="currency" )
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="currency_id", unique=true, nullable=false)
	private Long currencyId;

	@Column(length=1000)
	private String descriprion;

	@Column(name="iso_code", nullable=false, length=3)
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