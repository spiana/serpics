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
package com.serpics.membership.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="role_table")
@XmlRootElement
public class Role extends com.serpics.core.data.jpa.AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id", unique=true, nullable=false)
	private Long Id;

	@Size(max=254, message = "{role.description.size}")
	@Column(length=254)
	private String description;

	@NotNull(message = "{role.name.notnull}")
	@Column(nullable=false, length=80)
	private String name;

	//bi-directional many-to-one association to MembersRole
	@OneToMany(mappedBy="role",cascade = CascadeType.REMOVE)
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