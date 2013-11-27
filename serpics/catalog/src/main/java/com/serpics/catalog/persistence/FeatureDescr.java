package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ctentry_descr database table.
 * 
 */
@Entity
@Table(name="feature_descr" )
public class FeatureDescr extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CtentryDescrPK id;
	
	private String name;

  //bi-directional many-to-one association to Ctentry
    @ManyToOne
    @JoinColumn(name="feature_id" , insertable=false , updatable=false)
    private Feature feature;
    
    
    public FeatureDescr() {
    }


	public CtentryDescrPK getId() {
		return id;
	}


	public void setId(CtentryDescrPK id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Feature getFeature() {
		return feature;
	}


	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	
	
}