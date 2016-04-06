package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.serpics.catalog.data.CatalogEntryType;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "product_variants")
public class ProductVariant extends AbstractProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    public ProductVariant(final String sku , final boolean buyable ) {
        super();
        this.code = sku;
        this.ctentryType = CatalogEntryType.VARIANT;
       this.buyable = true;
    }

    public ProductVariant() {
        super();
        this.ctentryType = CatalogEntryType.VARIANT;
       
    }

    @ManyToOne(optional=true)
    @JoinColumn(name="parent_product")
    protected Product parentProduct;
    
    protected double sequence = 0.0;
   
    @OneToMany(mappedBy="product" , orphanRemoval=true , cascade=CascadeType.REMOVE , fetch=FetchType.LAZY)
    Set<VariantAttribute> attributes = new LinkedHashSet<VariantAttribute>(0);
    
	public Product getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(Product parentProduct) {
		this.parentProduct = parentProduct;
	}

	public double getSequence() {
		return sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public Set<VariantAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<VariantAttribute> attributes) {
		this.attributes = attributes;
	}
    

}