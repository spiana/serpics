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
	private Category category_parent;
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
	private Category category_child;

 
	public Category getCategory_parent() {
		return category_parent;
	}

	public void setCategory_parent(Category category_parent) {
		this.category_parent = category_parent;
	}

	public Category getCategory_child() {
		return category_child;
	}

	public void setCategory_child(Category category_child) {
		this.category_child = category_child;
	}

}
