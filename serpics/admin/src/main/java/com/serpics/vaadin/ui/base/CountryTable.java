package com.serpics.vaadin.ui.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Country;
import com.serpics.base.repositories.CountryRepository;
import com.serpics.core.data.Repository;
import com.serpics.membership.persistence.Role;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.EntityTable;

@VaadinComponent("countryTable")
public class CountryTable extends EntityTable<Country> {

	public CountryTable() {
		super(Country.class);
		
	}
	
	private static final long serialVersionUID = 1064176359644526596L;

	
	@Override
	public void init() {
		super.init();
		setPropertyToShow(new String[]{"description", "geocode", "iso2Code" , "regions"});
		
		EntityForm<Country> main = new EntityForm<Country>(Country.class) {
		};
				
		editorWindow.addTab(main, "main");
	}
}
