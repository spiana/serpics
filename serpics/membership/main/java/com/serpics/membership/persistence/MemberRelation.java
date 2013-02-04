package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DiscriminatorOptions;

import java.math.BigDecimal;
import java.util.Date;



/**
 * The persistent class for the member_relation database table.
 * 
 */
@MappedSuperclass
public abstract class MemberRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected MemberRelationPK id;

    @Column(length=250)
    protected String field1;

	@Column(length=200)
	private String field2;

	@Column(precision=10, scale=4)
	private BigDecimal field3;

	private double precedence;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

/*
	//bi-directional many-to-one association to Member
    @ManyToOne( fetch=FetchType.LAZY)
	@JoinColumn(name="parent_member_id", nullable=false, insertable=false, updatable=false )
	private Member parent_member;

	//bi-directional many-to-one association to Member
    @ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="child_member_id", nullable=false, insertable=false, updatable=false)
	private Member child_member;
  */  
    public MemberRelation() {
    }

	public MemberRelationPK getId() {
		return this.id;
	}

	public void setId(MemberRelationPK id) {
		this.id = id;
	}
	
	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public BigDecimal getField3() {
		return this.field3;
	}

	public void setField3(BigDecimal field3) {
		this.field3 = field3;
	}

	public double getPrecedence() {
		return this.precedence;
	}

	public void setPrecedence(double precedence) {
		this.precedence = precedence;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

		
	
}