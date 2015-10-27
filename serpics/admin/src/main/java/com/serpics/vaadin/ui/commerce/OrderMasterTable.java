package com.serpics.vaadin.ui.commerce;

import com.serpics.commerce.data.model.Order;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterTable;

@VaadinComponent("orderMasterTable")
public class OrderMasterTable extends MasterTable<Order>{

	public OrderMasterTable() {
		super(Order.class);
	}
	
	
}
