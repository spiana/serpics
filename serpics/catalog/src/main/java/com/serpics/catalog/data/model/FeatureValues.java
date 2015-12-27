package com.serpics.catalog.data.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.base.data.model.MultiValueAttribute;


@Entity
public class FeatureValues extends AbstractCatalogEntry{

	private static final long serialVersionUID = 5102019790904200289L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="featurevalue_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feature_id" , nullable=false)
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable=false)
    private AbstractProduct product;

    @Embedded
    MultiValueAttribute value = new MultiValueAttribute();

    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public Feature getFeature() {
        return feature;
    }
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }
    public AbstractProduct getProduct() {
        return product;
    }
    public void setProduct(final AbstractProduct product) {
        this.product = product;
    }
	public MultiValueAttribute getValue() {
		return value;
	}
	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}
	
}
