package com.serpics.vaadin.ui.base;

import com.serpics.base.data.model.Geocode;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.MultilingualStringConvert;

@VaadinComponent("geocodeTable")
public class GeoCodeTable extends MasterTable<Geocode> {

	public GeoCodeTable() {
		super(Geocode.class);
		
	}
	
	private static final long serialVersionUID = 1064176359644526596L;

	
	@Override
	public void init() {
		super.init();
		setPropertyToShow(new String[]{"code","description" });
		entityList.setConverter("description", new MultilingualStringConvert());
		
	}
	
	@Override
	public EntityFormWindow<Geocode> buildEntityWindow() {
		EntityFormWindow<Geocode> editorWindow = new EntityFormWindow<Geocode>();
	
			MasterForm<Geocode> main = new MasterForm<Geocode>(Geocode.class) {
				@Override
				public void init() {
					super.init();
					setDisplayProperties(new String[]{"code" , "description","countries"});
					
				}
			};
			
		editorWindow.addTab(main, "main");
		return editorWindow;
	}
}
