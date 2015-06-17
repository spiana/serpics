package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.serpics.base.data.model.Currency;
import com.serpics.core.data.jpa.AbstractEntity;

/**
 * The persistent class for the prices database table.
 * 
 */
@Entity
@Table(name = "prices")
public class Price extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prices_id")
    private Long pricesId;

    @Column(name = "product_cost")
    private Double productCost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "current_price")
    private Double currentPrice;

    @Column(name = "min_qty")
    private Double minQty;

    private Double precedence;

    @Column(name = "product_price")
    private Double productPrice;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "valid_from")
    private Date validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "valid_to")
    private Date validTo;

    // bi-directional many-to-one association to Pricelist
    @ManyToOne(optional = true)
    @JoinColumn(name = "pricelist_id", nullable = true)
    private Pricelist pricelist;

    // bi-directional many-to-one association to Product
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private AbstractProduct product;

    public Price() {
    }

    public Long getPricesId() {
        return pricesId;
    }

    public void setPricesId(final Long pricesId) {
        this.pricesId = pricesId;
    }

  
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(final Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getMinQty() {
        return minQty;
    }

    public void setMinQty(final Double minQty) {
        this.minQty = minQty;
    }

    public Double getPrecedence() {
        return precedence;
    }

    public void setPrecedence(final Double precedence) {
        this.precedence = precedence;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(final Double productPrice) {
        this.productPrice = productPrice;
    }


    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(final Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(final Date validTo) {
        this.validTo = validTo;
    }

    public Pricelist getPricelist() {
        return pricelist;
    }

    public void setPricelist(final Pricelist pricelist) {
        this.pricelist = pricelist;
    }

    public AbstractProduct getProduct() {
        return product;
    }

    public void setProduct(final AbstractProduct product) {
        this.product = product;
    }

	public Double getProductCost() {
		return productCost;
	}

	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}

}