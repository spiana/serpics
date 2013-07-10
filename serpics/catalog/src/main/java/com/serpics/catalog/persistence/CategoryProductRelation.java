package com.serpics.catalog.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="CategoryProductRelation")
@DiscriminatorValue("1")
public class CategoryProductRelation extends CtentryRelation {

	private static final long serialVersionUID = 1L;
	
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="ctentry_id_parent" , insertable=false, updatable=false)
	private Category category_parent;
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
	private AbstractProduct product_child;

 
	public Category getCategory_parent() {
		return category_parent;
	}

	public void setCategory_parent(Category category_parent) {
		this.category_parent = category_parent;
	}

	public AbstractProduct getProduct_child() {
		return product_child;
	}

	public void setProduct_child(AbstractProduct product_child) {
		this.product_child = product_child;
	}

}
