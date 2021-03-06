/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.membership.data.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.serpics.core.data.jpa.AbstractEntity;



/**
 * The persistent class for the member_relation database table.
 * 
 */
@MappedSuperclass
public abstract class MemberRelation extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected MemberRelationPK id;

	@Size(max=250, message = "{memberRelation.field1.size}")
    @Column(length=250)
    protected String field1;

	@Size(max=200, message = "{memberRelation.field2.size}")
	@Column(length=200)
	private String field2;

	@Column(precision=10, scale=4)
	private BigDecimal field3;

	private double precedence;


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
		
	
}