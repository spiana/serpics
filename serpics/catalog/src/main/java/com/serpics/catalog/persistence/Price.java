package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the prices database table.
 * 
 */
@Entity
@Table(name = "prices")
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "prices_id")
	private String pricesId;

	@Column(name = "product_cost")
	private Double ctentryCost;

	private Long currency;

	@Column(name = "current_price")
	private BigDecimal currentPrice;

	@Column(name = "min_qty")
	private Double minQty;

	private Double precedence;

	@Column(name = "product_price")
	private Double productPrice;

	private Timestamp updated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "valid_from")
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "valid_to")
	private Date validTo;

	// bi-directional many-to-one association to Pricelist
	@ManyToOne
	@JoinColumn(name = "pricelist_id")
	private Pricelist pricelist;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private AbstractProduct product;

	public Price() {
	}

	public String getPricesId() {
		return pricesId;
	}

	public void setPricesId(String pricesId) {
		this.pricesId = pricesId;
	}

	public Double getCtentryCost() {
		return ctentryCost;
	}

	public void setCtentryCost(Double ctentryCost) {
		this.ctentryCost = ctentryCost;
	}

	public Long getCurrency() {
		return currency;
	}

	public void setCurrency(Long currency) {
		this.currency = currency;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Double getMinQty() {
		return minQty;
	}

	public void setMinQty(Double minQty) {
		this.minQty = minQty;
	}

	public Double getPrecedence() {
		return precedence;
	}

	public void setPrecedence(Double precedence) {
		this.precedence = precedence;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	public AbstractProduct getProduct() {
		return product;
	}

	public void setProduct(AbstractProduct product) {
		this.product = product;
	}

}