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
package com.serpics.postman.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.serpics.base.data.model.AbstractStoreEntity;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.i18n.data.model.MultilingualText;
@Table(name="template_store")
@Entity
public class TemplateStore extends AbstractStoreEntity {

	private static final long serialVersionUID = 7680435587027401790L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "templatestore_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@ManyToOne(optional=false)
	@JoinColumn(name="template_type_id",nullable=false)
	private TemplateType templateType;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@OneToOne( cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "template_id")
	private MultilingualString templateSubjectMail;

	
	@OneToOne( cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "template_subject_id")
	private MultilingualText templateMail;

	@Column(name="active")
	private Boolean active;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TemplateType getTemplateType() {
		return templateType;
	}

	public void setTemplateType(TemplateType templateType) {
		this.templateType = templateType;
	}

	public MultilingualText getTemplateMail() {
		return templateMail;
	}

	public void setTemplateMail(MultilingualText templateMail) {
		this.templateMail = templateMail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultilingualString getTemplateSubjectMail() {
		return templateSubjectMail;
	}

	public void setTemplateSubjectMail(MultilingualString templateSubjectMail) {
		this.templateSubjectMail = templateSubjectMail;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
