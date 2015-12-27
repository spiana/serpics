package com.serpics.catalog.data.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.serpics.core.data.jpa.AbstractEntity;

@MappedSuperclass
public abstract class AbstractCatalogEntry extends AbstractEntity {

	 	@ManyToOne(optional = false  )
	    @JoinColumn(name = "catalog_id" )
	    protected Catalog catalog;

		public Catalog getCatalog() {
			return catalog;
		}

		public void setCatalog(Catalog catalog) {
			this.catalog = catalog;
		}
	
}
