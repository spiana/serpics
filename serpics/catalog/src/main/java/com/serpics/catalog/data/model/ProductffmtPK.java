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
package com.serpics.catalog.data.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the productffmt database table.
 * 
 */
@Embeddable
public class ProductffmtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="pricelist_id")
	private Long pricelistId;

	@Column(name="product_id")
	private Long productId;

    public ProductffmtPK() {
    }
	public Long getPricelistId() {
		return this.pricelistId;
	}
	public void setPricelistId(Long pricelistId) {
		this.pricelistId = pricelistId;
	}
	public Long getProductId() {
		return this.productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductffmtPK)) {
			return false;
		}
		ProductffmtPK castOther = (ProductffmtPK)other;
		return 
			this.pricelistId.equals(castOther.pricelistId)
			&& this.productId.equals(castOther.productId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pricelistId.hashCode();
		hash = hash * prime + this.productId.hashCode();
		
		return hash;
    }
}