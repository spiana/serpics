package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.serpics.core.persistence.jpa.AbstractEntity;


/**
 * The persistent class for the ctentry_relation database table.
 * 
 */
//@Entity
//@Table(name="ctentry_relation" )
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn (name="relation_type" , discriminatorType=DiscriminatorType.INTEGER)
@MappedSuperclass
public abstract class CtentryRelation extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CtentryRelationPK id;

    @Column(name="fiedl2")
    private BigDecimal field2;

    private String field1;

    @Column(name="relation_type" , nullable=false)
    protected Integer relationType;

    private double sequence;



    public CtentryRelation() {
    }
    /*
	public CtentryRelation(CtentryRelationPK id, int relationType, double sequence) {
		super();
		this.id = id;
		//this.relationType = relationType;
		this.sequence = sequence;
	}
     */
    public CtentryRelationPK getId() {
        return this.id;
    }

    public void setId(final CtentryRelationPK id) {
        this.id = id;
    }

    public BigDecimal getField2() {
        return this.field2;
    }

    public void setField2(final BigDecimal field2) {
        this.field2 = field2;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public Integer getRelationType() {
        return this.relationType;
    }

    public void setRelationType(final Integer relationType) {
        this.relationType = relationType;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

}