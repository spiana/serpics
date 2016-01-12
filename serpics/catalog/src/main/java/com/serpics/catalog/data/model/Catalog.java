package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.core.data.jpa.AbstractEntity;

/**
 * The persistent class for the catalog database table.
 * 
 */
@Entity
@Table(name = "catalog")
public class Catalog extends AbstractEntity implements
		com.serpics.core.data.model.Catalog, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "catalog_id")
	private Long id;

	private String code;

	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "name_string_id")
	private MultilingualString name;

	private boolean published;

	public Catalog() {
		published = true;
	}

		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MultilingualString getName() {
		return name;
	}

	public void setName(MultilingualString name) {
		this.name = name;
	}


	@Override
	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}