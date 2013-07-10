package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the member_relation database table.
 * 
 */
@Embeddable
public class MemberRelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="parent_member_id", nullable=false)
	private Long parentMemberId;

	@Column(name="child_member_id", nullable=false)
	private Long childMemberId;

    public MemberRelationPK() {
    }
	public MemberRelationPK(Long parentMemberId, Long childMemberId) {
		super();
		this.parentMemberId = parentMemberId;
		this.childMemberId = childMemberId;
	}
	public Long getParentMemberId() {
		return this.parentMemberId;
	}
	public void setParentMemberId(Long parentMemberId) {
		this.parentMemberId = parentMemberId;
	}
	public Long getChildMemberId() {
		return this.childMemberId;
	}
	public void setChildMemberId(Long childMemberId) {
		this.childMemberId = childMemberId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MemberRelationPK)) {
			return false;
		}
		MemberRelationPK castOther = (MemberRelationPK)other;
		return 
			this.parentMemberId.equals(castOther.parentMemberId)
			&& this.childMemberId.equals(castOther.childMemberId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.parentMemberId.hashCode();
		hash = hash * prime + this.childMemberId.hashCode();
		
		return hash;
    }
}