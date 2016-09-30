package com.serpics.vaadin.smc.ui.base;

import com.serpics.base.data.model.Currency;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterTable;

@VaadinComponent("currencyTable")
public class CurrencyTable  extends MasterTable<Currency>{

	public CurrencyTable() {
		super(Currency.class );
	}
	
	

}
