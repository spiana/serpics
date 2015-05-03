package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.BaseAttribute;
import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the ctentry_attributes database table.
 * 
 */
@Entity
@Table(name="ctentry_attributes" )
public class CtentryAttribute extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="attribute_id")
    private Long attributeId;

    @Column(name = "base_attributes_id")
    private BaseAttribute baseAttribute;

    private short islogical;

    private double sequence;


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

    public void setAttributeId(final Long attributeId) {
        this.attributeId = attributeId;
    }


    public short getIslogical() {
        return this.islogical;
    }

    public void setIslogical(final short islogical) {
        this.islogical = islogical;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }


    public CtentryAttributeValueDatetime getCtentryAttributeValueDatetime() {
        return this.ctentryAttributeValueDatetime;
    }

    public void setCtentryAttributeValueDatetime(final CtentryAttributeValueDatetime ctentryAttributeValueDatetime) {
        this.ctentryAttributeValueDatetime = ctentryAttributeValueDatetime;
    }

    public CtentryAttributeValueDecimal getCtentryAttributeValueDecimal() {
        return this.ctentryAttributeValueDecimal;
    }

    public void setCtentryAttributeValueDecimal(final CtentryAttributeValueDecimal ctentryAttributeValueDecimal) {
        this.ctentryAttributeValueDecimal = ctentryAttributeValueDecimal;
    }

    public CtentryAttributeValueLong getCtentryAttributeValueLong() {
        return this.ctentryAttributeValueLong;
    }

    public void setCtentryAttributeValueLong(final CtentryAttributeValueLong ctentryAttributeValueLong) {
        this.ctentryAttributeValueLong = ctentryAttributeValueLong;
    }

    public CtentryAttributeValueText getCtentryAttributeValueText() {
        return this.ctentryAttributeValueText;
    }

    public void setCtentryAttributeValueText(final CtentryAttributeValueText ctentryAttributeValueText) {
        this.ctentryAttributeValueText = ctentryAttributeValueText;
    }

    public CtentryAttributeValueVarchar getCtentryAttributeValueVarchar() {
        return this.ctentryAttributeValueVarchar;
    }

    public void setCtentryAttributeValueVarchar(final CtentryAttributeValueVarchar ctentryAttributeValueVarchar) {
        this.ctentryAttributeValueVarchar = ctentryAttributeValueVarchar;
    }

    public Ctentry getCtentry() {
        return this.ctentry;
    }

    public void setCtentry(final Ctentry ctentry) {
        this.ctentry = ctentry;
    }

    public BaseAttribute getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(final BaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

}