package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ctentry_attributes database table.
 * 
 */
@Entity
@Table(name="ctentry_attributes" )
public class CtentryAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	private Long attributeId;

	@Column(name="base_attributes_id")
	private Long baseAttributesId;

	private short islogical;

	private double sequence;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updated;

	//bi-directional one-to-one association to CtentryAttributeValueDatetime
	@OneToOne(mappedBy="ctentryAttribute")
	private CtentryAttributeValueDatetime ctentryAttributeValueDatetime;

	//bi-directional one-to-one association to CtentryAttributeValueDecimal
	@OneToOne(mappedBy="ctentryAttribute")
	private CtentryAttributeValueDecimal ctentryAttributeValueDecimal;

	//bi-directional one-to-one association to CtentryAttributeValueLong
	@OneToOne(mappedBy="ctentryAttribute")
	private CtentryAttributeValueLong ctentryAttributeValueLong;

	//bi-directional one-to-one association to CtentryAttributeValueText
	@OneToOne(mappedBy="ctentryAttribute")
	private CtentryAttributeValueText ctentryAttributeValueText;

	//bi-directional one-to-one association to CtentryAttributeValueVarchar
	@OneToOne(mappedBy="ctentryAttribute")
	private CtentryAttributeValueVarchar ctentryAttributeValueVarchar;

	//bi-directional many-to-one association to Ctentry
    @ManyToOne
	@JoinColumn(name="ctentry_id")
	private Ctentry ctentry;

    public CtentryAttribute() {
    }

	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public Long getBaseAttributesId() {
		return this.baseAttributesId;
	}

	public void setBaseAttributesId(Long baseAttributesId) {
		this.baseAttributesId = baseAttributesId;
	}

	public short getIslogical() {
		return this.islogical;
	}

	public void setIslogical(short islogical) {
		this.islogical = islogical;
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

	public CtentryAttributeValueDatetime getCtentryAttributeValueDatetime() {
		return this.ctentryAttributeValueDatetime;
	}

	public void setCtentryAttributeValueDatetime(CtentryAttributeValueDatetime ctentryAttributeValueDatetime) {
		this.ctentryAttributeValueDatetime = ctentryAttributeValueDatetime;
	}
	
	public CtentryAttributeValueDecimal getCtentryAttributeValueDecimal() {
		return this.ctentryAttributeValueDecimal;
	}

	public void setCtentryAttributeValueDecimal(CtentryAttributeValueDecimal ctentryAttributeValueDecimal) {
		this.ctentryAttributeValueDecimal = ctentryAttributeValueDecimal;
	}
	
	public CtentryAttributeValueLong getCtentryAttributeValueLong() {
		return this.ctentryAttributeValueLong;
	}

	public void setCtentryAttributeValueLong(CtentryAttributeValueLong ctentryAttributeValueLong) {
		this.ctentryAttributeValueLong = ctentryAttributeValueLong;
	}
	
	public CtentryAttributeValueText getCtentryAttributeValueText() {
		return this.ctentryAttributeValueText;
	}

	public void setCtentryAttributeValueText(CtentryAttributeValueText ctentryAttributeValueText) {
		this.ctentryAttributeValueText = ctentryAttributeValueText;
	}
	
	public CtentryAttributeValueVarchar getCtentryAttributeValueVarchar() {
		return this.ctentryAttributeValueVarchar;
	}

	public void setCtentryAttributeValueVarchar(CtentryAttributeValueVarchar ctentryAttributeValueVarchar) {
		this.ctentryAttributeValueVarchar = ctentryAttributeValueVarchar;
	}
	
	public Ctentry getCtentry() {
		return this.ctentry;
	}

	public void setCtentry(Ctentry ctentry) {
		this.ctentry = ctentry;
	}
	
}