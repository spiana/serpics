/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.serpics.catalog.data.CatalogEntryType;
import com.serpics.catalog.data.ProductApprovalStatus;


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
        this.status= ProductApprovalStatus.check;
    
    }

    public ProductVariant() {
        super();
        this.ctentryType = CatalogEntryType.VARIANT;  
        this.status= ProductApprovalStatus.check;
    }

    @ManyToOne(optional=true)
    @JoinColumn(name="parent_product")
    protected Product parentProduct;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="parent_variant")
    protected ProductVariant parentVariant;

    @OneToMany(mappedBy="parentVariant")
    @OrderBy("sequence")
    protected Set<ProductVariant> variants;
    
    protected double sequence = 0.0;
   
    @OneToMany(mappedBy="product" , orphanRemoval=true , cascade=CascadeType.REMOVE , fetch=FetchType.LAZY)
    Set<VariantAttribute> attributes = new LinkedHashSet<VariantAttribute>(0);
    
	public Product  getParentProduct() {
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


	public ProductVariant getParentVariant() {
		return parentVariant;
	}

	public void setParentVariant(ProductVariant parentVariant) {
		this.parentVariant = parentVariant;
	}

	public Set<ProductVariant> getVariants() {
		return variants;
	}

	public void setVariants(Set<ProductVariant> variants) {
		this.variants = variants;
	}
    

}