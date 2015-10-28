package com.serpics.catalog.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="FearureModelRelation")
public class FeatureModelRelation extends CtentryRelation {
	
	private static final long serialVersionUID = 1L;
	
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="ctentry_id_parent" , insertable=false, updatable=false)
	private FeatureModel parentSpecification;
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
	private FeatureModel childSpecification;

	public FeatureModel getParentSpecification() {
		return parentSpecification;
	}

	public void setParentSpecification(FeatureModel parentSpecification) {
		this.parentSpecification = parentSpecification;
	}

	public FeatureModel getChildSpecification() {
		return childSpecification;
	}

	public void setChildSpecification(FeatureModel childSpecification) {
		this.childSpecification = childSpecification;
	}


}
