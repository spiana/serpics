package com.serpics.catalog.data.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.serpics.membership.data.model.User;

//@Entity
public class UserPricelistRelation extends MemberPricelistRelation{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2565243026233191202L;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id" , insertable=false, updatable=false)
    private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
