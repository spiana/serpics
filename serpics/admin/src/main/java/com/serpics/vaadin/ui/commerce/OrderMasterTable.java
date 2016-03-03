package com.serpics.vaadin.ui.commerce;

import javax.annotation.Resource;

import com.serpics.commerce.OrderStatus;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.services.OrderService;
import com.serpics.membership.data.model.Address;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailForm;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;

@VaadinComponent("orderMasterTable")
public class OrderMasterTable extends MasterTable<Order>{

	@Resource
	OrderService orderService;
	
	public OrderMasterTable() {
		super(Order.class);
	}
	
	/* (non-Javadoc)
	 * @see com.serpics.vaadin.ui.MasterTable#buildEntityWindow()
	 */
	@Override
	public EntityFormWindow<Order> buildEntityWindow() {
		EntityFormWindow<Order> w = new EntityFormWindow<Order>();
		w.addTab(buildMainWindow(), "main");
		w.addTab(buildAddressWindow("billingAddress" ), "billingAddress");
		w.addTab(buildAddressWindow("shippingAddress"), "destinationAddress");
		
		return w;
	}
	
	
	private MasterForm<Order> buildMainWindow(){
		MasterForm<Order> main = new MasterForm<Order>(Order.class) {
			/* (non-Javadoc)
			 * @see com.serpics.vaadin.ui.MasterForm#save()
			 */
			@Override
			public void save() throws CommitException {
				// TODO Auto-generated method stub
				super.save();
				
				if (entityItem.getEntity().getStatus().equals(OrderStatus.CREATED))
					;
			}
			
		};
		
		return main;
	}
	private MasterDetailForm<Order,Address> buildAddressWindow(final String parentproperty){
		return new MasterDetailForm<Order,Address>(Address.class , parentproperty){};
	}
	
	
}
