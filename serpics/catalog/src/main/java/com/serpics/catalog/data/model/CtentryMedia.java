package com.serpics.catalog.data.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.base.data.model.Media;

@Entity
@Table(name="ctentry_media")
public class CtentryMedia extends Media{

	@ManyToOne
	@JoinColumn(name="ctentry_id")
	private Ctentry ctentry;

	public Ctentry getCtentry() {
		return ctentry;
	}

	public void setCtentry(Ctentry ctentry) {
		this.ctentry = ctentry;
	}
}
