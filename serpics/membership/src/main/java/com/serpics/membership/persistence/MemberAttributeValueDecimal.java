package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the member_attribute_value_decimal database table.
 * 
 */
@Entity
@Table(name="member_attribute_value_decimal")
@XmlRootElement
public class MemberAttributeValueDecimal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paramers_id", unique=true, nullable=false)
	private Long paramersId;

	private Timestamp updated;

	@Column(nullable=false, precision=10, scale=4)
	private BigDecimal value;

	//bi-directional one-to-one association to MemberAttribute
	@OneToOne
	@JoinColumn(name="paramers_id", nullable=false, insertable=false, updatable=false)
	private MemberAttribute memberAttribute;

    public MemberAttributeValueDecimal() {
    }

	public Long getParamersId() {
		return this.paramersId;
	}

	public void setParamersId(Long paramersId) {
		this.paramersId = paramersId;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public MemberAttribute getMemberAttribute() {
		return this.memberAttribute;
	}

	public void setMemberAttribute(MemberAttribute memberAttribute) {
		this.memberAttribute = memberAttribute;
	}
	
}