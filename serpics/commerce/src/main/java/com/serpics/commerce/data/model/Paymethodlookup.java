package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.data.model.Store;
import com.serpics.commerce.PaymentIntent;



/**
 * The persistent class for the paymethlookup database table.
 * 
 */
@Entity
@Table(name="paymethodlookup" )
public class Paymethodlookup extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymethodlookupPK id;

	@Column(nullable=false)
	private boolean active;


	//bi-directional many-to-one association to Store
    @ManyToOne
    @NotNull
	@JoinColumn(name="store_id", insertable=false, updatable=false)
	private Store store;

	//bi-directional many-to-one association to Paymethod
    @ManyToOne
    @NotNull
	@JoinColumn(name="paymethod_id", insertable=false, updatable=false)
	private Paymethod paymethod;

    private String merchantKey;
    
    private String merchantSecret;

    @Enumerated(EnumType.STRING)
    PaymentIntent intent ;
    
    
    String cancelURL;
    
    String returnURL;
    
    public Paymethodlookup() {
    }

	public PaymethodlookupPK getId() {
		return this.id;
	}

	public void setId(PaymethodlookupPK id) {
		this.id = id;
	}
	
	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Paymethod getPaymethod() {
		return this.paymethod;
	}

	public void setPaymethod(Paymethod paymethod) {
		this.paymethod = paymethod;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getMerchantSecret() {
		return merchantSecret;
	}

	public void setMerchantSecret(String merchantSecret) {
		this.merchantSecret = merchantSecret;
	}

	public PaymentIntent getIntent() {
		return intent;
	}

	public void setIntent(PaymentIntent intent) {
		this.intent = intent;
	}

	public String getCancelURL() {
		return cancelURL;
	}

	public void setCancelURL(String cancelURL) {
		this.cancelURL = cancelURL;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
	
}