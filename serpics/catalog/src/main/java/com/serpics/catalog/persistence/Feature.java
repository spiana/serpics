package com.serpics.catalog.persistence;



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

import com.serpics.base.persistence.MultilingualString;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

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

    public void setFeatureId(final Long featureId) {
        this.featureId = featureId;
    }


    public boolean isSequence() {
        return sequence;
    }

    public void setSequence(final boolean sequence) {
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

    public short getType() {
        return type;
    }

    public void setType(final short type) {
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


}
