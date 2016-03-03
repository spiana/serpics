package com.serpics.commerce.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.Currency;


/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="shipping" )
public class Shipping extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="shipping_id", unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="currency_id", nullable=false)
	private Currency currency;

	@Column(precision=10, scale=4)
	private Double rangestart;

	@Column(precision=10, scale=4)
	private Double value;

	//bi-directional many-to-one association to Shipmode
    @ManyToOne
	@JoinColumn(name="shipmode_id", nullable=false)
	private Shipmode shipmode;

    public Shipping() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long shippingId) {
		this.id = shippingId;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getRangestart() {
		return this.rangestart;
	}

	public void setRangestart(Double rangestart) {
		this.rangestart = rangestart;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Shipmode getShipmode() {
		return this.shipmode;
	}

	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}
	
}