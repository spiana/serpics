package com.serpics.catalog.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.serpics.catalog.data.CatalogEntryType;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@DiscriminatorValue("1")
public class Category extends Ctentry implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to CtentryRelation
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, targetEntity = CategoryRelation.class, cascade = CascadeType.REMOVE)
    @OrderBy("sequence desc")
    private Set<CategoryRelation> childCategories;

    // bi-directional many-to-one association to CtentryRelation
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, targetEntity = CategoryProductRelation.class,cascade = CascadeType.REMOVE)
    @OrderBy("sequence desc")
    private Set<CategoryProductRelation> childProducts;

    @Column(name = "meta_description")
    private String metaDescription;
    
	@Column(name = "meta_keyword")
    private String metaKeyword;

    protected Integer published;
    
	public Category() {
        this.ctentryType = CatalogEntryType.CATEGORY;
    }

    public Set<CategoryProductRelation> getChildProducts() {
        return childProducts;
    }

    public void setChildProducts(final Set<CategoryProductRelation> childProducts) {
        this.childProducts = childProducts;
    }

    public Set<CategoryRelation> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(final Set<CategoryRelation> childCategories) {
        this.childCategories = childCategories;
    }

    public String getMetaKeyword() {
		return metaKeyword;
	}

	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
	}
	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
    @PrePersist
    @Override
    public void beforePersist() {
        if (this.url == null)
            this.url = "/" + getCatalog().getCode() + "/c/" + getCode();
        super.beforePersist();
    }

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
	}

}