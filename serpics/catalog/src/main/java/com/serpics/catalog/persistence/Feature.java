package com.serpics.catalog.persistence;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.serpics.base.persistence.BaseAttribute;

@Entity
public class Feature  extends com.serpics.core.persistence.jpa.AbstractEntity{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="feature_id")
	private Long featureId;
	
//	@ManyToOne
//	@JoinColumn(name="base_attribute_id")
//	private BaseAttribute attribute;
	
	private String name;
	
	private short type;
	
	@OneToMany(mappedBy="feature")
	private Set<FeatureDescr> featureDescr = new HashSet<FeatureDescr>(0);
	
	@Column(name="sequence")
	private boolean sequence;
	
	@ManyToOne
	@JoinColumn(name="featureGroup_id")
	private FeatureGroup featureGroup;
	
	@OneToMany(mappedBy="feature" ,fetch= FetchType.EAGER)
	private Set<FeatureValues> featureValues = new HashSet<FeatureValues>(0);

	public Long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}

	
	public boolean isSequence() {
		return sequence;
	}

	public void setSequence(boolean sequence) {
		this.sequence = sequence;
	}

	public Set<FeatureValues> getFeatureValues() {
		return featureValues;
	}

	public void setFeatureValues(Set<FeatureValues> featureValues) {
		this.featureValues = featureValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Set<FeatureDescr> getFeatureDescr() {
		return featureDescr;
	}

	public void setFeatureDescr(Set<FeatureDescr> featureDescr) {
		this.featureDescr = featureDescr;
	}

	public FeatureGroup getFeatureGroup() {
		return featureGroup;
	}

	public void setFeatureGroup(FeatureGroup featureGroup) {
		this.featureGroup = featureGroup;
	}

	
	
}
