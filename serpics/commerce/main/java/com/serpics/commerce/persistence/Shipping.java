package com.serpics.commerce.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@Table(name="shipping" )
public class Shipping extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shipping_id", unique=true, nullable=false)
	private Long shippingId;

	@Column(nullable=false, length=3)
	private String currency;

	@Column(precision=10, scale=4)
	private BigDecimal rangestart;

	@Column(precision=10, scale=4)
	private BigDecimal value;

	//bi-directional many-to-one association to Shipmode
    @ManyToOne
	@JoinColumn(name="shipmode_id", nullable=false)
	private Shipmode shipmode;

    public Shipping() {
    }

	public Long getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getRangestart() {
		return this.rangestart;
	}

	public void setRangestart(BigDecimal rangestart) {
		this.rangestart = rangestart;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Shipmode getShipmode() {
		return this.shipmode;
	}

	public void setShipmode(Shipmode shipmode) {
		this.shipmode = shipmode;
	}
	
}