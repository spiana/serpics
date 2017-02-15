package com.serpics.catalog.data.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.core.data.jpa.AbstractEntity;
import com.serpics.membership.data.model.Member;

@Entity
public  class MemberPricelistRelation  extends AbstractEntity{
	private static final long serialVersionUID = -527591768485015980L;


	@EmbeddedId
	MemberPricelistRelationPK id ;
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id" , insertable=false, updatable=false)
    private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

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
