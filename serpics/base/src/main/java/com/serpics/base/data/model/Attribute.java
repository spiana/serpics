package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the ctentry_attributes database table.
 * 
 */
@Embeddable
public class Attribute extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="attribute_id")
    private Long id;
   
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="base_attribute_id" ,updatable=false)
    private BaseAttribute baseAttribute;
    
    private double sequence;

    @Embedded
    private MultiValueAttribute value;
    
    public Attribute() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }
  
    public BaseAttribute getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(final BaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

	public MultiValueAttribute getValue() {
		return value;
	}

	public void setValue(MultiValueAttribute value) {
		this.value = value;
	}

}