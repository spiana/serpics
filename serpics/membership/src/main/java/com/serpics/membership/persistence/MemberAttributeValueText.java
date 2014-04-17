package com.serpics.membership.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the member_attribute_value_varchar database table.
 * 
 */
@Entity
@Table(name = "member_attribute_value_text")
@XmlRootElement
public class MemberAttributeValueText implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="paramers_id", unique=true, nullable=false)
    private Long paramersId;

    @Column(nullable=false)
    private Timestamp updated;

    @Lob
    private String value;

    //bi-directional one-to-one association to MemberAttribute
    @OneToOne
    @JoinColumn(name="paramers_id", nullable=false, insertable=false, updatable=false)
    private MemberAttribute memberAttribute;

    public MemberAttributeValueText() {
    }

    public Long getParamersId() {
        return this.paramersId;
    }

    public void setParamersId(final Long paramersId) {
        this.paramersId = paramersId;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(final Timestamp updated) {
        this.updated = updated;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public MemberAttribute getMemberAttribute() {
        return this.memberAttribute;
    }

    public void setMemberAttribute(final MemberAttribute memberAttribute) {
        this.memberAttribute = memberAttribute;
    }

}