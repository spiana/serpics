package com.serpics.catalog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberPricelistRelationPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9209774612404728112L;

	@Column(name="priceList_id" , nullable=false)
	Long priceListId;
	
	@Column(name="member_id" , nullable=false)
	Long memberId;

	public MemberPricelistRelationPK() {
		super();
	}
	
	public MemberPricelistRelationPK(Long memberId, Long priceListId) {
		super();
		this.memberId = memberId;
		this.priceListId = priceListId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((priceListId == null) ? 0 : priceListId.hashCode());
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
		MemberPricelistRelationPK other = (MemberPricelistRelationPK) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (priceListId == null) {
			if (other.priceListId != null)
				return false;
		} else if (!priceListId.equals(other.priceListId))
			return false;
		return true;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	
	
	
}
