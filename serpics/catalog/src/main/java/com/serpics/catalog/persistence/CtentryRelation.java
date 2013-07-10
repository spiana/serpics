package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DiscriminatorOptions;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ctentry_relation database table.
 * 
 */
@Entity
@Table(name="ctentry_relation" )
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="relation_type" , discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorOptions(force=true)
public abstract class CtentryRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CtentryRelationPK id;

	@Column(name="fiedl2")
	private BigDecimal field2;

	private String field1;

	@Column(name="relation_type" ,insertable=false ,updatable=false)
	private int relationType;

	private double sequence;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updated;

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

	public void setId(CtentryRelationPK id) {
		this.id = id;
	}
	
	public BigDecimal getField2() {
		return this.field2;
	}

	public void setField2(BigDecimal field2) {
		this.field2 = field2;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public int getRelationType() {
		return this.relationType;
	}

	public void setRelationType(int relationType) {
		this.relationType = relationType;
	}

	public double getSequence() {
		return this.sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@PrePersist
	@PreUpdate
	public void beforeUpdate(){
		setUpdated(new Date());
	}
	
	
}