package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;



/**
 * The persistent class for the attribute_lookup database table.
 * 
 */
@Entity
@Table(name="attribute_lookup" )
public class AttributeLookup extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AttributeLookupPK id;

    @Column(nullable=false)
    private double sequence;

    //bi-directional many-to-one association to BaseAttribute
    @ManyToOne
    @JoinColumn(name="base_attributes_id", nullable=false, insertable=false, updatable=false)
    private BaseAttribute baseAttribute;


    //    @JoinColumn(name="store_id" , nullable=false , insertable=false , updatable=false )
    //    StoreRealm store;

    public AttributeLookup() {
    }

    public AttributeLookupPK getId() {
        return this.id;
    }

    public void setId(final AttributeLookupPK id) {
        this.id = id;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public BaseAttribute getBaseAttribute() {
        return this.baseAttribute;
    }

    public void setBaseAttribute(final BaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }



}