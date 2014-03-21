package com.serpics.base.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the base_attributes database table.
 * 
 */
@Entity
@Table(name = "base_attributes")

public class BaseAttribute extends com.serpics.core.persistence.jpa.Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "base_attributes_id", unique = true, nullable = false)
    private Long baseAttributesId;

    @Column(name = "attribute_type", nullable = false)
    private short attributeType;

    @Column(nullable = false )
    private String availablefor;

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

    // bi-directional many-to-one association to BaseAttributeDescr
    @OneToMany(mappedBy = "baseAttribute")
    private Set<BaseAttributeDescr> baseAttributeDescrs;

    public BaseAttribute() {
    }

    public Long getBaseAttributesId() {
        return this.baseAttributesId;
    }

    public void setBaseAttributesId(final Long baseAttributesId) {
        this.baseAttributesId = baseAttributesId;
    }

    public short getAttributeType() {
        return this.attributeType;
    }

    public void setAttributeType(final short attributeType) {
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

    public Set<BaseAttributeDescr> getBaseAttributeDescrs() {
        return this.baseAttributeDescrs;
    }

    public void setBaseAttributeDescrs(final Set<BaseAttributeDescr> baseAttributeDescrs) {
        this.baseAttributeDescrs = baseAttributeDescrs;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    public String getAvailablefor() {
        return availablefor;
    }

    public void setAvailablefor(final String availablefor) {
        this.availablefor = availablefor;
    }



}