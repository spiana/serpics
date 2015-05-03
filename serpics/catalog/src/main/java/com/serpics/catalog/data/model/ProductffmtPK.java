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