package com.serpics.vaadin.ui.catalog;

import com.serpics.catalog.data.model.Brand;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterTable;

@VaadinComponent("brandTable")
public class BrandTable extends MasterTable<Brand>{

	
	private static final long serialVersionUID = -1774417523705825395L;

	public BrandTable() {
		super(Brand.class);
	}

}
