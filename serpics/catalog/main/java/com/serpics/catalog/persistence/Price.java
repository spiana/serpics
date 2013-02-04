package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the prices database table.
 * 
 */
@Entity
@Table(name="prices" )
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="prices_id")
	private String pricesId;

	@Column(name="ctentry_cost")
	private BigDecimal ctentryCost;

	private BigInteger currency;

	@Column(name="current_price")
	private BigDecimal currentPrice;

	@Column(name="min_qty")
	private BigDecimal minQty;

	private BigDecimal precedence;

	@Column(name="product_price")
	private BigDecimal productPrice;

	private Timestamp updated;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="valid_from")
	private Date validFrom;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="valid_to")
	private Date validTo;

	//bi-directional many-to-one association to Pricelist
    @ManyToOne
	@JoinColumn(name="pricelist_id")
	private Pricelist pricelist;

	//bi-directional many-to-one association to Product
    @ManyToOne
	@JoinColumn(name="product_id")
	private AbstractProduct product;

    public Price() {
    }

	public String getPricesId() {
		return this.pricesId;
	}

	public void setPricesId(String pricesId) {
		this.pricesId = pricesId;
	}

	public BigDecimal getCtentryCost() {
		return this.ctentryCost;
	}

	public void setCtentryCost(BigDecimal ctentryCost) {
		this.ctentryCost = ctentryCost;
	}

	public BigInteger getCurrency() {
		return this.currency;
	}

	public void setCurrency(BigInteger currency) {
		this.currency = currency;
	}

	public BigDecimal getCurrentPrice() {
		return this.currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getMinQty() {
		return this.minQty;
	}

	public void setMinQty(BigDecimal minQty) {
		this.minQty = minQty;
	}

	public BigDecimal getPrecedence() {
		return this.precedence;
	}

	public void setPrecedence(BigDecimal precedence) {
		this.precedence = precedence;
	}

	public BigDecimal getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Pricelist getPricelist() {
		return this.pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}
	
	public AbstractProduct getProduct() {
		return this.product;
	}

	public void setProduct(AbstractProduct product) {
		this.product = product;
	}
	
}