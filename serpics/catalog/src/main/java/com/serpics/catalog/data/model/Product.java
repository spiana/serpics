package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.serpics.base.data.model.TaxCategory;
import com.serpics.catalog.data.CatalogEntryType;
import com.serpics.core.datatype.ProductType;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "abstractProducts")
@DiscriminatorValue("3")
//@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.INTEGER)
public class Product extends Ctentry implements Serializable {
    private static final long serialVersionUID = 1L;

    public Product(final boolean buyable, final String sku) {
        super();
        this.buyable = buyable;
        this.published = buyable;
        this.code = sku;
        this.downlodable = false;
        this.ctentryType = 1;
    }

    public Product() {
        super();
        this.buyable = this.published =true; this.downlodable = false;
        this.ctentryType = CatalogEntryType.PRODUCT;
        this.productType = ProductType.PRODUCT;
    }

    @Column(name = "buyable", nullable = false)
    protected boolean buyable;

    @Column(name = "downlodable", nullable = false)
    protected boolean downlodable;

    @Column(name = "manufacturer_sku")
    protected String manufacturerSku;

    @Column(name = "product_type", nullable = false)
    protected Integer productType;

    @Column(name = "published", nullable = false)
    protected boolean published;

    @Column(name = "unit_meas")
    protected String unitMeas;

    @Column(name = "weight")
    protected Double weight;

    @Column(name = "weight_meas")
    protected String weightMeas;

    // bi-directional many-to-one association to Price
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    protected Set<Price> prices;

    // bi-directional many-to-one association to Brand
    @ManyToOne(optional = true)
    @JoinColumn(name = "brand_id")
    protected Brand brand;

    // bi-directional many-to-one association to Productffmt
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    protected Set<Productffmt> productffmts;
    
    @ManyToOne(optional=true)
    @JoinColumn(name="featuremodel_id")
    protected FeatureModel featureModel;

    @OneToMany(mappedBy="product" , orphanRemoval=true , cascade=CascadeType.REMOVE , fetch=FetchType.LAZY)
    Set<FeatureValues> featureValues = new HashSet<FeatureValues>(0);

 // bi-directional many-to-one association to CtentryRelation
    @OneToMany(mappedBy = "childProduct", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = CategoryProductRelation.class,cascade = CascadeType.REMOVE)
    @OrderBy("sequence desc")
    private Set<CategoryProductRelation> categories;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "meta_keyword")
    private String metaKeyword;
    
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

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(final Brand brand) {
        this.brand = brand;
    }

    public Set<Productffmt> getProductffmts() {
        return this.productffmts;
    }

    public void setProductffmts(final Set<Productffmt> productffmts) {
        this.productffmts = productffmts;
    }

   

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(final Integer productType) {
        this.productType = productType;
    }

    

    @PrePersist
    public void prepersist(){
        if (this.url == null)
            this.url = "/" + getCatalog().getCode() + "/p/" + getCode();
    }

    public FeatureModel getfeautureModel() {
        return featureModel;
    }

    public void setFeautureModel(final FeatureModel featureModel) {
        this.featureModel = featureModel;
    }

    public Set<FeatureValues> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(final Set<FeatureValues> featureValues) {
        this.featureValues = featureValues;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(final String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(final String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

	public Set<CategoryProductRelation> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryProductRelation> categories) {
		this.categories = categories;
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

	public TaxCategory getTaxcategory() {
		return taxcategory;
	}

	public void setTaxcategory(TaxCategory taxcategory) {
		this.taxcategory = taxcategory;
	}
}