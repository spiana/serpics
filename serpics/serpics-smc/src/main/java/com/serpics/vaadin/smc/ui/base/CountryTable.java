package com.serpics.vaadin.smc.ui.base;

import com.serpics.base.data.model.Country;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
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
	public EntityFormWindow<Country> buildEntityWindow() {
		
		EntityFormWindow<Country> editorWindow = new EntityFormWindow<Country>();
	
			MasterForm<Country> main = new MasterForm<Country>(Country.class) {
			};
			
		editorWindow.addTab(main, I18nUtils.getMessage("country.main", ""));
		return editorWindow;
	}
}
