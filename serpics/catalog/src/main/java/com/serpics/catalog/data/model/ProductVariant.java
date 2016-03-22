package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.serpics.catalog.data.CatalogEntryType;


/**
 * The persistent class for the product database table.
 * 
 */
//@Entity
//@Table(name = "variants")
public class ProductVariant extends Ctentry implements Serializable {
    private static final long serialVersionUID = 1L;

    public ProductVariant(final boolean buyable, final String sku) {
        super();
        this.code = sku;
        this.ctentryType = CatalogEntryType.PRODUCT;
    }

    public ProductVariant() {
        super();
        this.ctentryType = CatalogEntryType.PRODUCT;
    }

    @ManyToOne(optional=true)
    @JoinColumn(name="parent_product")
    protected AbstractProduct parentProduct;
   
	// bi-directional many-to-one association to Productffmt
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    protected Set<Productffmt> productffmts;
    

}