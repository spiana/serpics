package com.serpics.catalog.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ctentry_attribute_value_long database table.
 * 
 */
@Entity
@Table(name="ctentry_attribute_value_long")
public class CtentryAttributeValueLong implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	private String attributeId;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updated;

    @Lob()
	private Long value;

	//bi-directional one-to-one association to CtentryAttribute
	@OneToOne
	@JoinColumn(name="attribute_id")
	private CtentryAttribute ctentryAttribute;

    public CtentryAttributeValueLong() {
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

	public Long getValue() {
		return this.value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public CtentryAttribute getCtentryAttribute() {
		return this.ctentryAttribute;
	}

	public void setCtentryAttribute(CtentryAttribute ctentryAttribute) {
		this.ctentryAttribute = ctentryAttribute;
	}
	
}