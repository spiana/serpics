package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Catalog2StoreRelPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4216397067579188522L;
	
	
	@Column(name="store_id")
	Long storeId;
	
	@Column(name="catalog_id")
	Long catalogId;
	
	public Catalog2StoreRelPK() {
		super();
	}
	
	public Catalog2StoreRelPK(Long storeId, Long catalogId) {
		super();
		this.storeId = storeId;
		this.catalogId = catalogId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((catalogId == null) ? 0 : catalogId.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catalog2StoreRelPK other = (Catalog2StoreRelPK) obj;
		if (catalogId == null) {
			if (other.catalogId != null)
				return false;
		} else if (!catalogId.equals(other.catalogId))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	
}
