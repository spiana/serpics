package com.serpics.catalog.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ctentry_attribute_value_datetime database table.
 * 
 */
@Entity
@Table(name="ctentry_attribute_value_datetime")
public class CtentryAttributeValueDatetime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	private Long attributeId;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updated;

    @Temporal( TemporalType.TIMESTAMP)
	private Date value;

	//bi-directional one-to-one association to CtentryAttribute
	@OneToOne
	@JoinColumn(name="attribute_id")
	private CtentryAttribute ctentryAttribute;

    public CtentryAttributeValueDatetime() {
    }

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getValue() {
		return this.value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public CtentryAttribute getCtentryAttribute() {
		return this.ctentryAttribute;
	}

	public void setCtentryAttribute(CtentryAttribute ctentryAttribute) {
		this.ctentryAttribute = ctentryAttribute;
	}
	
}