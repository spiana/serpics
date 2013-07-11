package com.serpics.membership.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", unique=true, nullable=false)
	private Long roleId;

	@Column(length=254)
	private String description;

	@Column(nullable=false, length=80)
	private String name;

	//bi-directional many-to-one association to MembersRole
	@OneToMany(mappedBy="role")
	private Set<MembersRole> membersRoles;

    public Role() {
    }

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MembersRole> getMembersRoles() {
		return this.membersRoles;
	}

	public void setMembersRoles(Set<MembersRole> membersRoles) {
		this.membersRoles = membersRoles;
	}
	
}