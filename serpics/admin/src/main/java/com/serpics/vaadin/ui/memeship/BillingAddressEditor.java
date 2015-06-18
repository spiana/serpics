package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.BillingAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterDetailForm;

@VaadinComponent("billingAddressEditor")
public class BillingAddressEditor extends MasterDetailForm<UsersReg,BillingAddress> {
    private static final long serialVersionUID = 1L;

 
    public BillingAddressEditor() {
        super(BillingAddress.class);
    }

    @Override
    public void init() {
    	setParentProperty("billingAddress");
    	setHideProperties(new String[] {"member"});
    	setDisplayProperties(new String[] {"firstname", "lastname" , "company", "vatcode" , "address1" , "zipcode", "city" , "email" , "mobile" , "phone" , "fax" , "created", "updated"});
    	setReadOnlyProperties(new String[]{"created" , "updated"});
    }

}
