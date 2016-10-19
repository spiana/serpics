/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.vaadin.smc.ui.commerce;

import javax.annotation.Resource;

import com.serpics.commerce.OrderStatus;
import com.serpics.commerce.data.model.Order;
import com.serpics.commerce.data.model.Payment;
import com.serpics.commerce.services.OrderService;
import com.serpics.membership.data.model.Address;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailForm;
import com.serpics.vaadin.ui.MasterDetailTable;
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
		w.addTab(buildPaymentForm("payments" ), "payments");
	//	w.addTab(buildAddressWindow("shippingAddress"), "destinationAddress");
		
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
	
	private MasterDetailTable<Payment, Order> buildPaymentForm(final String parentProperty){
		return new MasterDetailTable<Payment, Order>(Payment.class , parentProperty){
			
		};
	}
	private MasterDetailForm<Order,Address> buildAddressWindow(final String parentproperty){
		return new MasterDetailForm<Order,Address>(Address.class , parentproperty){};
	}
	
	
}
