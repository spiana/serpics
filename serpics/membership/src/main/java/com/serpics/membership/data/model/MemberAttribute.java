package com.serpics.membership.data.model;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.serpics.base.data.model.BaseAttribute;
import com.serpics.core.data.jpa.AbstractEntity;


/**
 * The persistent class for the member_attribute database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name = "member_attribute")
public class MemberAttribute extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_id", unique = true, nullable = false)
    private Long attributeId;

    @ManyToOne()
    @JoinColumn(name = "base_attributes_id", nullable=false)
    BaseAttribute baseAttribute;

    private double sequence;

    // bi-directional many-to-one association to Member
    @ManyToOne
    @XmlTransient
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

    public void setAttributeId(final Long attributeId) {
        this.attributeId = attributeId;
    }


    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(final Member member) {
        this.member = member;
    }

    public MemberAttributeValueDatetime getMemberAttributeValueDatetime() {
        return this.memberAttributeValueDatetime;
    }

    public void setMemberAttributeValueDatetime(final MemberAttributeValueDatetime memberAttributeValueDatetime) {
        this.memberAttributeValueDatetime = memberAttributeValueDatetime;
    }

    public MemberAttributeValueDecimal getMemberAttributeValueDecimal() {
        return this.memberAttributeValueDecimal;
    }

    public void setMemberAttributeValueDecimal(final MemberAttributeValueDecimal memberAttributeValueDecimal) {
        this.memberAttributeValueDecimal = memberAttributeValueDecimal;
    }

    public MemberAttributeValueLong getMemberAttributeValueLong() {
        return this.memberAttributeValueLong;
    }

    public void setMemberAttributeValueLong(final MemberAttributeValueLong memberAttributeValueLong) {
        this.memberAttributeValueLong = memberAttributeValueLong;
    }

    public MemberAttributeValueText getMemberAttributeValueText() {
        return this.memberAttributeValueText;
    }

    public void setMemberAttributeValueText(final MemberAttributeValueText memberAttributeValueText) {
        this.memberAttributeValueText = memberAttributeValueText;
    }

    public MemberAttributeValueVarchar getMemberAttributeValueVarchar() {
        return this.memberAttributeValueVarchar;
    }

    public void setMemberAttributeValueVarchar(final MemberAttributeValueVarchar memberAttributeValueVarchar) {
        this.memberAttributeValueVarchar = memberAttributeValueVarchar;
    }

    public BaseAttribute getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(final BaseAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

}