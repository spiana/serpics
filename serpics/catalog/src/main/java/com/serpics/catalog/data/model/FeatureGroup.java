package com.serpics.catalog.data.model;

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

import com.serpics.base.data.model.MultilingualString;

@Entity
public class FeatureGroup extends com.serpics.core.data.jpa.AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setFeatureGroupId(final Long id) {
        this.id = id;
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
