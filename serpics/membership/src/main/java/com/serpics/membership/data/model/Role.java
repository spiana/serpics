package com.serpics.membership.data.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



import java.util.Set;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@XmlRootElement
public class Role extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", unique=true, nullable=false)
	private Long Id;

	@Column(length=254)
	private String description;

	@Column(nullable=false, length=80)
	private String name;

	//bi-directional many-to-one association to MembersRole
	@OneToMany(mappedBy="role")
	private Set<MembersRole> membersRoles;

    public Role() {
    }

	public Role( String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return this.Id;
	}

	public void setId(Long roleId) {
		this.Id = roleId;
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