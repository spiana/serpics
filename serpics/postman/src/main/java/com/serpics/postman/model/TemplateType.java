package com.serpics.postman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;

@Table(name="template_type")
@Entity
public class TemplateType extends AbstractEntity{

	private static final long serialVersionUID = 773233177127804482L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "templatetype_id", unique = true, nullable = false)
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
