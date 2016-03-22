package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.serpics.base.data.model.TaxCategory;
import com.serpics.catalog.data.ProductType;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "abstractProducts")
public abstract class AbstractProduct extends Ctentry implements Serializable {
    private static final long serialVersionUID = 1L;

   
    @Column(name = "manufacturer_sku")
    protected String manufacturerSku;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_type", nullable = false)
    protected ProductType productType;

    @Column(name = "buyable", nullable = false)
    protected boolean buyable;

    
    @Column(name = "unit_meas")
    protected String unitMeas;

    @Column(name = "weight")
    protected Double weight;

    @Column(name = "weight_meas")
    protected String weightMeas;

    // bi-directional many-to-one association to Price
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    protected Set<Price> prices;
   
	// bi-directional many-to-one association to Productffmt
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    protected Set<Productffmt> productffmts;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "taxcategory_id")
    private TaxCategory taxcategory;

    public String getManufacturerSku() {
        return this.manufacturerSku;
    }

    public void setManufacturerSku(final String manufacturerSku) {
        this.manufacturerSku = manufacturerSku;
    }

    public String getUnitMeas() {
        return this.unitMeas;
    }

    public void setUnitMeas(final String unitMeas) {
        this.unitMeas = unitMeas;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(final Double weight) {
        this.weight = weight;
    }

    public String getWeightMeas() {
        return this.weightMeas;
    }

    public void setWeightMeas(final String weightMeas) {
        this.weightMeas = weightMeas;
    }

    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(final Set<Price> prices) {
        this.prices = prices;
    }


    public Set<Productffmt> getProductffmts() {
        return this.productffmts;
    }

    public void setProductffmts(final Set<Productffmt> productffmts) {
        this.productffmts = productffmts;
    }

    @PrePersist
    public void prepersist(){
        if (this.url == null)
            this.url = "/" + getCatalog().getCode() + "/p/" + getCode();
    }

	public com.serpics.catalog.data.ProductType getProductType() {
		return productType;
	}

	public void setProductType(com.serpics.catalog.data.ProductType productType) {
		this.productType = productType;
	}

	public TaxCategory getTaxcategory() {
		return taxcategory;
	}

	public void setTaxcategory(TaxCategory taxcategory) {
		this.taxcategory = taxcategory;
	}

}