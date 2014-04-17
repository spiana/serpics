package com.serpics.catalog.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.serpics.base.persistence.MultilingualString;

@Entity
public class FeatureGroup extends com.serpics.core.persistence.jpa.AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long featureGroupId;

    private String code;

    private String name;

    private double sequence;

    @ManyToOne
    @JoinColumn(name="specification_id" , nullable=false)
    Specification specification;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_string_id")
    private MultilingualString description;

    @OneToMany(mappedBy="featureGroup")
    Set<Feature> features = new HashSet<Feature>(0);

    public Long getFeatureGroupId() {
        return featureGroupId;
    }

    public void setFeatureGroupId(final Long featureGroupId) {
        this.featureGroupId = featureGroupId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getSequence() {
        return sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(final Specification specification) {
        this.specification = specification;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(final Set<Feature> features) {
        this.features = features;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }
}
