package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.base.AttributeType;
import com.serpics.base.AvailableforType;
import com.serpics.core.data.jpa.AbstractEntity;

/**
 * The persistent class for the base_attributes database table.
 * 
 */
@Entity
@Table(name = "base_attributes")
public class BaseAttribute extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "base_attributes_id", unique = true, nullable = false)
    private Long baseAttributesId;

    @Column(name = "attribute_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AttributeType attributeType;

    @Column(nullable = false )
    @Enumerated(EnumType.STRING)
    private AvailableforType availablefor;

    @Column(nullable = false)
    private short displayas;

    @Column(nullable = false)
    private short issearchable;

    @Column(nullable = false, length = 100)
    private String name;

    // bi-directional many-to-one association to AttributeLookup
    //	@OneToMany(mappedBy = "baseAttribute")
    //	private Set<AttributeLookup> attributeLookups;

    @Column(name="store_id" , nullable=false)
    private Long storeId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    public BaseAttribute() {
    }

    public Long getBaseAttributesId() {
        return this.baseAttributesId;
    }

    public void setBaseAttributesId(final Long baseAttributesId) {
        this.baseAttributesId = baseAttributesId;
    }

    public AttributeType getAttributeType() {
        return this.attributeType;
    }

    public void setAttributeType(final AttributeType attributeType) {
        this.attributeType = attributeType;
    }


    public short getDisplayas() {
        return this.displayas;
    }

    public void setDisplayas(final short displayas) {
        this.displayas = displayas;
    }

    public short getIssearchable() {
        return this.issearchable;
    }

    public void setIssearchable(final short issearchable) {
        this.issearchable = issearchable;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    public AvailableforType getAvailablefor() {
        return availablefor;
    }

    public void setAvailablefor(final AvailableforType availablefor) {
        this.availablefor = availablefor;
    }

    public MultilingualString getDescription() {
        return description;
    }

    public void setDescription(final MultilingualString description) {
        this.description = description;
    }


}