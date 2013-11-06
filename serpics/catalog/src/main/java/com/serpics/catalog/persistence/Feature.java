package com.serpics.catalog.persistence;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.serpics.base.persistence.BaseAttribute;

@Entity
public class Feature  extends com.serpics.core.persistence.jpa.Entity{

	@Id
	@Column(name="feature_id")
	private Long featureId;
	
	@OneToMany
	@JoinColumn(name="attribute_id")
	private BaseAttribute attribute;
	
	@Column(name="sequence")
	private boolean sequence;
	
	@OneToMany(mappedBy="feature" ,fetch= FetchType.EAGER)
	private Set<FeatureValues> featureValues = new HashSet<FeatureValues>(0);
	
}
