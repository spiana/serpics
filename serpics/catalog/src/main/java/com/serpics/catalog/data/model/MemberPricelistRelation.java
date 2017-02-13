package com.serpics.catalog.data.model;

import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.serpics.core.data.jpa.AbstractEntity;

@MappedSuperclass
public abstract class MemberPricelistRelation  extends AbstractEntity{
	
	@EmbeddedId
	MemberPricelistRelationPK id ;
	

    //bi-directional many-to-one association to Ctentry
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="priceList_id" , insertable=false, updatable=false )
    private Pricelist priceList;


	public MemberPricelistRelationPK getId() {
		return id;
	}


	public void setId(MemberPricelistRelationPK id) {
		this.id = id;
	}


	public Pricelist getPriceList() {
		return priceList;
	}


	public void setPriceList(Pricelist priceList) {
		this.priceList = priceList;
	}
}
