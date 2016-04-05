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
import com.serpics.base.data.model.MultilingualText;
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
	
	@OneToOne( cascade = { CascadeType.ALL }, orphanRemoval = true , fetch= FetchType.EAGER)
    @JoinColumn(name = "template_id")
	private MultilingualText templateMail;

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
	
	
}
