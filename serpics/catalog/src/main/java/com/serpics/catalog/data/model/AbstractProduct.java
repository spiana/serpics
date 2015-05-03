package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.serpics.core.datatype.CatalogEntryType;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name = "abstractProducts")
@DiscriminatorValue("3")
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.INTEGER)
@PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "ctentry_id")
public abstract class AbstractProduct extends Ctentry implements Serializable {
    private static final long serialVersionUID = 1L;

    public AbstractProduct(final Integer buyable, final String sku) {
        super();
        this.buyable = buyable;
        this.published = buyable;
        this.code = sku;
        this.downlodable = 0;
        this.ctentryType = 1;
    }

    public AbstractProduct() {
        super();
        this.buyable = this.published = this.downlodable = 0;
        this.ctentryType = CatalogEntryType.PRODUCT;
    }

    @Column(name = "buyable", nullable = false)
    protected Integer buyable;

    @Column(name = "downlodable", nullable = false)
    protected Integer downlodable;

    @Column(name = "manufacturer_sku")
    protected String manufacturerSku;

    @Column(name = "product_type", nullable = false)
    protected Integer productType;

    @Column(name = "published", nullable = false)
    protected Integer published;

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
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    protected Set<Productffmt> productffmts;

    // bi-directional many-to-one association to Catalog
    @ManyToOne(optional = false  )
    @JoinColumn(name = "catalog_id" )
    protected Catalog catalog;

    @ManyToOne(optional=true)
    @JoinColumn(name="specification_id")
    protected Specification specification;

    @OneToMany(mappedBy="product" , orphanRemoval=true , cascade=CascadeType.REMOVE , fetch=FetchType.LAZY)
    Set<FeatureValues> featureValues = new HashSet<FeatureValues>(0);


    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "meta_keyword")
    private String metaKeyword;

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

    public Integer getBuyable() {
        return buyable;
    }

    public void setBuyable(final Integer buyable) {
        this.buyable = buyable;
    }

    public Integer getDownlodable() {
        return downlodable;
    }

    public void setDownlodable(final Integer downlodable) {
        this.downlodable = downlodable;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(final Integer productType) {
        this.productType = productType;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(final Integer published) {
        this.published = published;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(final Catalog catalog) {
        this.catalog = catalog;
    }

    @PrePersist
    public void prepersist(){
        if (this.url == null)
            this.url = "/" + getCatalog().getCode() + "/product/" + getCode();
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(final Specification specification) {
        this.specification = specification;
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
}