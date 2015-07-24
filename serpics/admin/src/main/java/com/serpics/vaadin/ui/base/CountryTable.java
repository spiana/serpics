package com.serpics.vaadin.ui.base;

import com.serpics.base.data.model.Country;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;

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
		//setPropertyToShow(new String[]{"description", "geocode.code", "iso2Code" });
		//entityList.setConverter("description", new MultilingualStringConvert());
	}
	
	@Override
	public EntityFormWindow<Country> buildEntityWindow() {
		EntityFormWindow<Country> editorWindow = new EntityFormWindow<Country>();
	
			MasterForm<Country> main = new MasterForm<Country>(Country.class) {
				@Override
				public void init() {
					super.init();
					//setHideProperties(new String[]{"regions"});
				}
			};
			
		editorWindow.addTab(main, "main");
		return editorWindow;
	}
}
