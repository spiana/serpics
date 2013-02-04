package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the paymethod_descr database table.
 * 
 */
@Entity
@Table(name="paymethod_descr" )
public class PaymethodDescr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymethodDescrPK id;

	@Column(nullable=false, length=1000)
	private String description;

	//bi-directional many-to-one association to Paymethod
    @ManyToOne
	@JoinColumn(name="paymethod_id", nullable=false, insertable=false, updatable=false)
	private Paymethod paymethod;

	//bi-directional many-to-one association to Locale
    @ManyToOne
	@JoinColumn(name="locale_id", nullable=false, insertable=false, updatable=false)
	private Locale locale;

    public PaymethodDescr() {
    }

	public PaymethodDescrPK getId() {
		return this.id;
	}

	public void setId(PaymethodDescrPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Paymethod getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}
	
	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}