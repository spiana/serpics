package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the member_attribute_value_long database table.
 * 
 */
@Entity
@Table(name="member_attribute_value_long")
@XmlRootElement
public class MemberAttributeValueLong implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="attribute_id", unique=true, nullable=false)
	private Long attributeId;

	@Column(nullable=false)
	private Timestamp updated;

    @Lob()
	@Column(nullable=false)
	private String value;

	//bi-directional one-to-one association to MemberAttribute
	@OneToOne
	@JoinColumn(name="attribute_id", nullable=false, insertable=false, updatable=false)
	private MemberAttribute memberAttribute;

    public MemberAttributeValueLong() {
    }

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MemberAttribute getMemberAttribute() {
		return this.memberAttribute;
	}

	public void setMemberAttribute(MemberAttribute memberAttribute) {
		this.memberAttribute = memberAttribute;
	}
	
}