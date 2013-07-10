package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the base_attribute_descr database table.
 * 
 */
@Embeddable
public class BaseAttributeDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="locale_id", unique=true, nullable=false)
	private Long localeId;

	@Column(name="attributes_id", unique=true, nullable=false)
	private Long attributesId;

    public BaseAttributeDescrPK() {
    }
	public Long getLocaleId() {
		return this.localeId;
	}
	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
	}
	public Long getAttributesId() {
		return this.attributesId;
	}
	public void setAttributesId(Long attributesId) {
		this.attributesId = attributesId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BaseAttributeDescrPK)) {
			return false;
		}
		BaseAttributeDescrPK castOther = (BaseAttributeDescrPK)other;
		return 
			this.localeId.equals(castOther.localeId)
			&& this.attributesId.equals(castOther.attributesId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.localeId.hashCode();
		hash = hash * prime + this.attributesId.hashCode();
		
		return hash;
    }
}