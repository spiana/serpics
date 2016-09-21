package com.serpics.catalog.data.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import com.serpics.base.AttributeType;
import com.serpics.base.data.model.MultilingualString;
@Entity
public class Feature  extends AbstractCatalogEntry{

	private static final long serialVersionUID = -1111581062897750056L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="feature_id")
    private Long id;

	@Pattern(regexp="[a-zA-Z0-9_-]*", message= "{feature.name.pattern}")
    private String name;

    private AttributeType type;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    @Column(name="sequence")
    private double sequence;

    @ManyToOne
    @JoinColumn(name="featureModel_id" , nullable=false)
    private FeatureModel featureModel;
    
    @ManyToOne
    @JoinColumn(name="featureGroup_id" ,nullable=false)
    private FeatureGroup featureGroup;

    @OneToMany(mappedBy="feature" ,fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FeatureValues> featureValues = new HashSet<FeatureValues>(0);

    public Long getId() {
        return id;
    }

    public void setId(final Long featureId) {
        this.id = featureId;
    }


    public double isSequence() {
        return sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public Set<FeatureValues> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(final Set<FeatureValues> featureValues) {
        this.featureValues = featureValues;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(final AttributeType type) {
        this.type = type;
    }


    public FeatureGroup getFeatureGroup() {
        return featureGroup;
    }

    public void setFeatureGroup(final FeatureGroup featureGroup) {
        this.featureGroup = featureGroup;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }

	public FeatureModel getFeatureModel() {
		return featureModel;
	}

	public void setFeatureModel(FeatureModel featureModel) {
		this.featureModel = featureModel;
	}

	

}
