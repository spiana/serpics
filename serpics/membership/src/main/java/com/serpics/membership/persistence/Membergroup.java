package com.serpics.membership.persistence;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the membergroups database table.
 * 
 */
@Entity
@Table(name="membergroups")
@XmlRootElement
public class Membergroup  extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="membergroups_id", unique=true, nullable=false)
	private Long membergroupsId;

	@Column(length=254)
	private String description;

	@Column(nullable=false, length=40)
	private String name;


	//bi-directional many-to-one association to Store
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

    public Membergroup() {
    }

	public Long getMembergroupsId() {
		return this.membergroupsId;
	}

	public void setMembergroupsId(Long membergroupsId) {
		this.membergroupsId = membergroupsId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}