package com.serpics.base.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the base_attribute_descr database table.
 * 
 */
@Entity
@Table(name="base_attribute_descr" )
public class BaseAttributeDescr extends com.serpics.core.persistence.jpa.Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BaseAttributeDescrPK id;

	@Column(nullable=false, length=1000)
	private String description;

	//bi-directional many-to-one association to BaseAttribute
    @ManyToOne
	@JoinColumn(name="attributes_id", nullable=false, insertable=false, updatable=false)
	private BaseAttribute baseAttribute;

	//bi-directional many-to-one association to Locale
    @ManyToOne
	@JoinColumn(name="locale_id", nullable=false, insertable=false, updatable=false)
	private Locale locale;

    public BaseAttributeDescr() {
    }

	public BaseAttributeDescrPK getId() {
		return this.id;
	}

	public void setId(BaseAttributeDescrPK id) {
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseAttribute getBaseAttribute() {
		return this.baseAttribute;
	}

	public void setBaseAttribute(BaseAttribute baseAttribute) {
		this.baseAttribute = baseAttribute;
	}
	
	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}