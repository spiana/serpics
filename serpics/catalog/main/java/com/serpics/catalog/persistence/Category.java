package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@DiscriminatorValue("0")
@PrimaryKeyJoinColumn(name = "category_id")
public class Category extends Ctentry implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to CtentryRelation
	@OneToMany(mappedBy = "category_parent", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = CategoryRelation.class)
	@OrderBy("sequence desc")
	private Set<CategoryRelation> childCategories;

	// bi-directional many-to-one association to CtentryRelation
	@OneToMany(mappedBy = "category_parent", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = CategoryProductRelation.class)
	@OrderBy("sequence desc")
	private Set<CategoryProductRelation> childProducts;

	public Category() {
	}

	public Set<CategoryProductRelation> getChildProducts() {
		return childProducts;
	}

	public void setChildProducts(Set<CategoryProductRelation> childProducts) {
		this.childProducts = childProducts;
	}

	public Set<CategoryRelation> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<CategoryRelation> childCategories) {
		this.childCategories = childCategories;
	}

}