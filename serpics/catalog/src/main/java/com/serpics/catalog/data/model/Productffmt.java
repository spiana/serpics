package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the productffmt database table.
 * 
 */
@Entity
@Table(name = "productffmt")
public class Productffmt implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductffmtPK id;

    @Temporal(TemporalType.DATE)
    @Column(name = "available_date")
    private Date availableDate;

    @Column(name = "available_in_order")
    private Double availableInOrder;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    // bi-directional many-to-one association to Pricelist
    @ManyToOne
    @JoinColumn(name = "pricelist_id", insertable = false, updatable = false)
    private Pricelist pricelist;

    // bi-directional many-to-one association to Product
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private BaseProduct product;

    public Productffmt() {
    }

    public ProductffmtPK getId() {
        return this.id;
    }

    public void setId(final ProductffmtPK id) {
        this.id = id;
    }

    public Date getAvailableDate() {
        return this.availableDate;
    }

    public void setAvailableDate(final Date availableDate) {
        this.availableDate = availableDate;
    }

    public Double getAvailableInOrder() {
        return this.availableInOrder;
    }

    public void setAvailableInOrder(final Double availableInOrder) {
        this.availableInOrder = availableInOrder;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    public Pricelist getPricelist() {
        return this.pricelist;
    }

    public void setPricelist(final Pricelist pricelist) {
        this.pricelist = pricelist;
    }

    public BaseProduct getProduct() {
        return this.product;
    }

    public void setProduct(final BaseProduct product) {
        this.product = product;
    }

}