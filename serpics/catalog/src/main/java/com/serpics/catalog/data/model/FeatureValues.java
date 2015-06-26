package com.serpics.catalog.data.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.serpics.base.data.model.MultilingualString;

@Entity
public class FeatureValues extends com.serpics.core.data.jpa.AbstractEntity {

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

    @Temporal(TemporalType.DATE)
    private Date dateValue;
    private Long longValue;
    private Double decimalValue;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "value_string_id")
    private MultilingualString stringValue;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "text_string_id")
    private MultilingualString textValue;


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

    public Date getDateValue() {
        return dateValue;
    }
    public void setDateValue(final Date dateValue) {
        this.dateValue = dateValue;
    }
    public Long getLongValue() {
        return longValue;
    }
    public void setLongValue(final Long longValue) {
        this.longValue = longValue;
    }
    public Double getDecimalValue() {
        return decimalValue;
    }
    public void setDecimalValue(final Double decimalValue) {
        this.decimalValue = decimalValue;
    }

    public MultilingualString getStringValue() {
        return stringValue;
    }

    public void setStringValue(final MultilingualString stringValue) {
        this.stringValue = stringValue;
    }

    public MultilingualString getTextValue() {
        return textValue;
    }

    public void setTextValue(final MultilingualString textValue) {
        this.textValue = textValue;
    }

}
