package com.serpics.catalog.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="CategoryRelation")
@DiscriminatorValue("0")
public class CategoryRelation extends CtentryRelation {

	private static final long serialVersionUID = 1L;
	
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="ctentry_id_parent" , insertable=false, updatable=false)
	private Category parentCategory;
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
	private Category childCategory;

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Category getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(Category childCategory) {
		this.childCategory = childCategory;
	}

 



}
