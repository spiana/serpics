/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.base.data.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the attribute_lookup database table.
 * 
 */
@Embeddable
public class AttributeLookupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="base_attributes_id", unique=true, nullable=false)
	private Long baseAttributesId;

	@Column(name="store_id", unique=true, nullable=false)
	private Long storeId;

    public AttributeLookupPK() {
    }
	public Long getBaseAttributesId() {
		return this.baseAttributesId;
	}
	public void setBaseAttributesId(Long baseAttributesId) {
		this.baseAttributesId = baseAttributesId;
	}
	public Long getStoreId() {
		return this.storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AttributeLookupPK)) {
			return false;
		}
		AttributeLookupPK castOther = (AttributeLookupPK)other;
		return 
			this.baseAttributesId.equals(castOther.baseAttributesId)
			&& this.storeId.equals(castOther.storeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.baseAttributesId.hashCode();
		hash = hash * prime + this.storeId.hashCode();
		
		return hash;
    }
}