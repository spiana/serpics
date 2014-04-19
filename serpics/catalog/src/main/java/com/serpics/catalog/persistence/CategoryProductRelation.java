package com.serpics.catalog.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="CategoryProductRelation")
@DiscriminatorValue("1")
public class CategoryProductRelation extends CtentryRelation {

    public CategoryProductRelation() {
        super();
        relationType = 1;

    }

    private static final long serialVersionUID = 1L;


    //bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ctentry_id_parent" , insertable=false, updatable=false)
    private Category parentCategory;

    //bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
    private AbstractProduct childProduct;

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(final Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public AbstractProduct getChildProduct() {
        return childProduct;
    }

    public void setChildProduct(final AbstractProduct childProduct) {
        this.childProduct = childProduct;
    }




}
