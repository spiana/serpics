package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the members_role database table.
 * 
 */
@Entity
@Table(name="members_role" )
public class MembersRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MembersRolePK id;

	@Column(nullable=false)
	private Timestamp updated;

	//bi-directional many-to-one association to Member
    @ManyToOne
	@JoinColumn(name="member_id", nullable=false, insertable=false, updatable=false)
	private Member member;

	//bi-directional many-to-one association to Role
    @ManyToOne
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private Role role;

	//bi-directional many-to-one association to Store
    @ManyToOne
	@JoinColumn(name="store_id", nullable=false, insertable=false, updatable=false)
	private Store store;

    public MembersRole() {
    }

	public MembersRolePK getId() {
		return this.id;
	}

	public void setId(MembersRolePK id) {
		this.id = id;
	}
	
	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}