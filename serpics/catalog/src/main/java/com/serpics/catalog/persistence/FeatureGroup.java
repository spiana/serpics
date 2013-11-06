package com.serpics.catalog.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class FeatureGroup extends com.serpics.core.persistence.jpa.Entity {
	private Long featureGroupId;
	private String name;	
	private double sequence;
	Set<Feature> features = new HashSet<Feature>(0);
}
