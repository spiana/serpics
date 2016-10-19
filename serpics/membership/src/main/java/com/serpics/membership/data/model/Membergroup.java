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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.serpics.base.data.model.AbstractStoreEntity;




/**
 * The persistent class for the membergroups database table.
 * 
 */
@Entity
@Table(name="membergroups")
@XmlRootElement
public class Membergroup  extends AbstractStoreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="membergroups_id", unique=true, nullable=false)
    private Long id;

    @NotNull(message="{membergroup.name.notnull}")
    @Size(max = 100, message ="{membergroup.name.size}")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 254, message ="{membergroup.description.size}")
    @Column(length=254)
    private String description;

   
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "membergroup", cascade = CascadeType.ALL)
    private Set<Membergrouprel> groupRelation = new HashSet<Membergrouprel>(0);

    public Membergroup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long membergroupsId) {
        this.id = membergroupsId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public Set<Membergrouprel> getGroupRelation() {
        return groupRelation;
    }

    public void setGroupRelation(final Set<Membergrouprel> groupRelation) {
        this.groupRelation = groupRelation;
    }

}