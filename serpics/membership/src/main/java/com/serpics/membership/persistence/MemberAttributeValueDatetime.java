package com.serpics.membership.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the member_attribute_value_datetime database table.
 * 
 */
@Entity
@Table(name = "member_attribute_value_datetime")
@XmlRootElement
public class MemberAttributeValueDatetime extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attrribute_id", unique = true, nullable = false)
	private Long attrributeId;

	@Column(nullable = false)
	private Timestamp value;

	// bi-directional one-to-one association to MemberAttribute
	@OneToOne
	@JoinColumn(name = "attrribute_id", nullable = false, insertable = false, updatable = false)
	private MemberAttribute memberAttribute;

	public MemberAttributeValueDatetime() {
	}

	public Long getAttrributeId() {
		return this.attrributeId;
	}

	public void setAttrributeId(Long attrributeId) {
		this.attrributeId = attrributeId;
	}

	public Timestamp getValue() {
		return this.value;
	}

	public void setValue(Timestamp value) {
		this.value = value;
	}

	public MemberAttribute getMemberAttribute() {
		return this.memberAttribute;
	}

	public void setMemberAttribute(MemberAttribute memberAttribute) {
		this.memberAttribute = memberAttribute;
	}

}