package com.serpics.vaadin.ui.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.model.Country;
import com.serpics.base.data.repositories.CountryRepository;
import com.serpics.core.data.Repository;
import com.serpics.membership.data.model.Role;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.MultilingualStringConvert;

@VaadinComponent("countryTable")
public class CountryTable extends MasterTable<Country> {

	public CountryTable() {
		super(Country.class);
		
	}
	
	private static final long serialVersionUID = 1064176359644526596L;

	
	@Override
	public void init() {
		super.init();
		container.addNestedContainerProperty("geocode.*");
		setPropertyToShow(new String[]{"description", "geocode.code", "iso2Code" });
		entityList.setConverter("description", new MultilingualStringConvert());
	}
	
	@Override
	public EntityFormWindow<Country> buildEntityWindow() {
		EntityFormWindow<Country> editorWindow = new EntityFormWindow<Country>();
	
			EntityForm<Country> main = new EntityForm<Country>(Country.class) {
				@Override
				public void init() {
					super.init();
					setHideProperties(new String[]{"regions"});
				}
			};
			
		editorWindow.addTab(main, "main");
		return editorWindow;
	}
}
