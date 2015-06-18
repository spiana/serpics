package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.data.model.PrimaryAddress;
import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterDetailForm;

@VaadinComponent("primaryAddressEditor")
public class PrimaryAddressEditor extends MasterDetailForm<UsersReg,PrimaryAddress>{
    private static final long serialVersionUID = 1L;

 
    public PrimaryAddressEditor() {
        super(PrimaryAddress.class);
    }

    @Override
    public void init() {
     	setParentProperty("primaryAddress");
    	setHideProperties(new String[] {"member"});
    	setDisplayProperties(new String[] {"firstname", "lastname" , "company", "vatcode" , "address1" , "zipcode", "city" , "email" , "mobile" , "phone" , "fax" , "created", "updated"});
    	setReadOnlyProperties(new String[]{"created" , "updated"});
       
    }

    
}
