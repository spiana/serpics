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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.serpics.catalog.data.CatalogEntryType;
import com.serpics.catalog.data.ProductApprovalStatus;
import com.serpics.catalog.data.ProductType;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "products")
public class Product extends AbstractProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    public Product(final boolean buyable, final String sku) {
        super();
        this.buyable = buyable;
        this.published = buyable;
        this.code = sku;
        this.downlodable = false;
        this.ctentryType = CatalogEntryType.PRODUCT;
        this.status= ProductApprovalStatus.check;
    }

    public Product() {
        super();
        this.buyable = this.published =true; this.downlodable = false;
        this.ctentryType = CatalogEntryType.PRODUCT;
        this.productType = ProductType.SINGLE;
        this.status= ProductApprovalStatus.check;
    }
    
    // bi-directional many-to-one association to Brand
    @ManyToOne(optional = true)
    @JoinColumn(name = "brand_id")
    protected Brand brand;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_type", nullable = false , updatable = false)
    protected ProductType productType;

    @Column(name = "published", nullable = false)
    protected boolean published;

    @ManyToOne(optional=true)
    @JoinColumn(name="featuremodel_id" ,updatable=false)
    protected FeatureModel featureModel;
    
    @OneToMany(mappedBy="parentProduct")
    @OrderBy("sequence")
    protected Set<ProductVariant> variants;
   
    @OneToMany(mappedBy="product" , orphanRemoval=true , cascade=CascadeType.REMOVE , fetch=FetchType.LAZY)
    Set<FeatureValues> featureValues = new LinkedHashSet<FeatureValues>(0);

 // bi-directional many-to-one association to CtentryRelation
    @OneToMany(mappedBy = "childProduct", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = CategoryProductRelation.class,cascade = CascadeType.REMOVE)
    @OrderBy("sequence ASC")
    private Set<CategoryProductRelation> categories;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public boolean isBuyable() {
		return buyable;
	}

	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}

	public boolean isDownlodable() {
		return downlodable;
	}

	public void setDownlodable(boolean downlodable) {
		this.downlodable = downlodable;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public FeatureModel getFeatureModel() {
		return featureModel;
	}

	public void setFeatureModel(FeatureModel featureModel) {
		this.featureModel = featureModel;
	}

	public Set<FeatureValues> getFeatureValues() {
		return featureValues;
	}

	public void setFeatureValues(Set<FeatureValues> featureValues) {
		this.featureValues = featureValues;
	}

	public Set<CategoryProductRelation> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryProductRelation> categories) {
		this.categories = categories;
	}

	public Set<ProductVariant> getVariants() {
		return variants;
	}

	public void setVariants(Set<ProductVariant> variants) {
		this.variants = variants;
	}
    

}