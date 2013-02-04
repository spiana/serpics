package com.serpics.base.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the base_attributes database table.
 * 
 */
@Entity
@Table(name="base_attributes" )
public class BaseAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="base_attributes_id", unique=true, nullable=false)
	private Long baseAttributesId;

	@Column(name="attribute_type", nullable=false)
	private short attributeType;

	@Column(nullable=false)
	private short avalablefor;

    @Lob()
	private String defaulvalue;

	@Column(nullable=false)
	private short displayas;

	@Column(nullable=false)
	private short issearchable;

	@Column(nullable=false, length=100)
	private String name;

	//bi-directional many-to-one association to AttributeLookup
	@OneToMany(mappedBy="baseAttribute")
	private Set<AttributeLookup> attributeLookups;

	//bi-directional many-to-one association to BaseAttributeDescr
	@OneToMany(mappedBy="baseAttribute")
	private Set<BaseAttributeDescr> baseAttributeDescrs;

    public BaseAttribute() {
    }

	public Long getBaseAttributesId() {
		return this.baseAttributesId;
	}

	public void setBaseAttributesId(Long baseAttributesId) {
		this.baseAttributesId = baseAttributesId;
	}

	public short getAttributeType() {
		return this.attributeType;
	}

	public void setAttributeType(short attributeType) {
		this.attributeType = attributeType;
	}

	public short getAvalablefor() {
		return this.avalablefor;
	}

	public void setAvalablefor(short avalablefor) {
		this.avalablefor = avalablefor;
	}

	public String getDefaulvalue() {
		return this.defaulvalue;
	}

	public void setDefaulvalue(String defaulvalue) {
		this.defaulvalue = defaulvalue;
	}

	public short getDisplayas() {
		return this.displayas;
	}

	public void setDisplayas(short displayas) {
		this.displayas = displayas;
	}

	public short getIssearchable() {
		return this.issearchable;
	}

	public void setIssearchable(short issearchable) {
		this.issearchable = issearchable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AttributeLookup> getAttributeLookups() {
		return this.attributeLookups;
	}

	public void setAttributeLookups(Set<AttributeLookup> attributeLookups) {
		this.attributeLookups = attributeLookups;
	}
	
	public Set<BaseAttributeDescr> getBaseAttributeDescrs() {
		return this.baseAttributeDescrs;
	}

	public void setBaseAttributeDescrs(Set<BaseAttributeDescr> baseAttributeDescrs) {
		this.baseAttributeDescrs = baseAttributeDescrs;
	}
	
}