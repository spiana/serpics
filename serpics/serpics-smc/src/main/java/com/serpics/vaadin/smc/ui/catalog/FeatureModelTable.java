 package com.serpics.vaadin.smc.ui.catalog;

import com.serpics.catalog.data.model.FeatureModel;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterTable;

@VaadinComponent("featureModelTable")
public class FeatureModelTable extends MasterTable<FeatureModel>{

	public FeatureModelTable() {
		super(FeatureModel.class);
	}

}
