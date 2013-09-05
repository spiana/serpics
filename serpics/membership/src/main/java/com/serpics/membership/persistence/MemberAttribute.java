package com.serpics.membership.persistence;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the member_attribute database table.
 * 
 */
@Entity
@Table(name = "member_attribute")
public class MemberAttribute extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attribute_id", unique = true, nullable = false)
	private Long attributeId;

	@Column(name = "base_attributes_id", nullable = false)
	private BigInteger baseAttributesId;

	private double sequence;

	// bi-directional many-to-one association to Member
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	// bi-directional one-to-one association to MemberAttributeValueDatetime
	@OneToOne(mappedBy = "memberAttribute")
	private MemberAttributeValueDatetime memberAttributeValueDatetime;

	// bi-directional one-to-one association to MemberAttributeValueDecimal
	@OneToOne(mappedBy = "memberAttribute")
	private MemberAttributeValueDecimal memberAttributeValueDecimal;

	// bi-directional one-to-one association to MemberAttributeValueLong
	@OneToOne(mappedBy = "memberAttribute")
	private MemberAttributeValueLong memberAttributeValueLong;

	// bi-directional one-to-one association to MemberAttributeValueText
	@OneToOne(mappedBy = "memberAttribute")
	private MemberAttributeValueText memberAttributeValueText;

	// bi-directional one-to-one association to MemberAttributeValueVarchar
	@OneToOne(mappedBy = "memberAttribute")
	private MemberAttributeValueVarchar memberAttributeValueVarchar;

	public MemberAttribute() {
	}

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public BigInteger getBaseAttributesId() {
		return this.baseAttributesId;
	}

	public void setBaseAttributesId(BigInteger baseAttributesId) {
		this.baseAttributesId = baseAttributesId;
	}

	public double getSequence() {
		return this.sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemberAttributeValueDatetime getMemberAttributeValueDatetime() {
		return this.memberAttributeValueDatetime;
	}

	public void setMemberAttributeValueDatetime(MemberAttributeValueDatetime memberAttributeValueDatetime) {
		this.memberAttributeValueDatetime = memberAttributeValueDatetime;
	}

	public MemberAttributeValueDecimal getMemberAttributeValueDecimal() {
		return this.memberAttributeValueDecimal;
	}

	public void setMemberAttributeValueDecimal(MemberAttributeValueDecimal memberAttributeValueDecimal) {
		this.memberAttributeValueDecimal = memberAttributeValueDecimal;
	}

	public MemberAttributeValueLong getMemberAttributeValueLong() {
		return this.memberAttributeValueLong;
	}

	public void setMemberAttributeValueLong(MemberAttributeValueLong memberAttributeValueLong) {
		this.memberAttributeValueLong = memberAttributeValueLong;
	}

	public MemberAttributeValueText getMemberAttributeValueText() {
		return this.memberAttributeValueText;
	}

	public void setMemberAttributeValueText(MemberAttributeValueText memberAttributeValueText) {
		this.memberAttributeValueText = memberAttributeValueText;
	}

	public MemberAttributeValueVarchar getMemberAttributeValueVarchar() {
		return this.memberAttributeValueVarchar;
	}

	public void setMemberAttributeValueVarchar(MemberAttributeValueVarchar memberAttributeValueVarchar) {
		this.memberAttributeValueVarchar = memberAttributeValueVarchar;
	}

}