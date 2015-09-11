package com.serpics.catalog.data.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="SpecificatonRelation")
public class SpecificationRelation extends CtentryRelation {
	
	private static final long serialVersionUID = 1L;
	
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="ctentry_id_parent" , insertable=false, updatable=false)
	private Specification parentSpecification;
	
	//bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ctentry_id_child" , insertable=false, updatable=false )
	private Specification childSpecification;

	public Specification getParentSpecification() {
		return parentSpecification;
	}

	public void setParentSpecification(Specification parentSpecification) {
		this.parentSpecification = parentSpecification;
	}

	public Specification getChildSpecification() {
		return childSpecification;
	}

	public void setChildSpecification(Specification childSpecification) {
		this.childSpecification = childSpecification;
	}


}
