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
import javax.validation.constraints.NotNull;

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
    private Long id;

    @Column(name = "attribute_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private AttributeType attributeType;

    @Column(nullable = false )
    @Enumerated(EnumType.STRING)
    @NotNull
    private AvailableforType availablefor;

    @Column(nullable = false, length = 100)
    @NotNull
    private String name;
    
    @Column(name="store_id" , nullable= false)
    private Long storeId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_stringid")
    private MultilingualString description;

    public BaseAttribute() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public AttributeType getAttributeType() {
        return this.attributeType;
    }

    public void setAttributeType(final AttributeType attributeType) {
        this.attributeType = attributeType;
    }


    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}