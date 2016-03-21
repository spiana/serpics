package com.serpics.postman.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.serpics.base.data.model.AbstractStoreEntity;
@Table(name="template_store")
@Entity
public class TemplateStore extends AbstractStoreEntity {

	private static final long serialVersionUID = 7680435587027401790L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "templatestore_id", unique = true, nullable = false)
	private Long id;
	
	@JoinColumn(name="template_type_id",nullable=false)
	private TemplateType templateType;
	
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name="template")
	private String templateMail;

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

	public String getTemplateMail() {
		return templateMail;
	}

	public void setTemplateMail(String templateMail) {
		this.templateMail = templateMail;
	}
	
	
}
