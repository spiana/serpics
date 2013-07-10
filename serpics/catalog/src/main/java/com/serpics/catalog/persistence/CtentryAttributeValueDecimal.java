package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ctentry_attribute_value_decimal database table.
 * 
 */
@Entity
@Table(name="ctentry_attribute_value_decimal")
public class CtentryAttributeValueDecimal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	private String attributeId;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updated;

	private BigDecimal value;

	//bi-directional one-to-one association to CtentryAttribute
	@OneToOne
	@JoinColumn(name="attribute_id")
	private CtentryAttribute ctentryAttribute;

    public CtentryAttributeValueDecimal() {
    }

	public String getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public CtentryAttribute getCtentryAttribute() {
		return this.ctentryAttribute;
	}

	public void setCtentryAttribute(CtentryAttribute ctentryAttribute) {
		this.ctentryAttribute = ctentryAttribute;
	}
	
}