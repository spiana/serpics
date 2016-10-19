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
package com.serpics.vaadin.smc.ui.memeship;

import com.serpics.membership.data.model.UsersReg;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.MasterForm;

@VaadinComponent("userEditorComponent")
public class UserEditorComponent extends MasterForm<UsersReg> {
    private static final long serialVersionUID = 1L;

    public UserEditorComponent() {
        super(UsersReg.class);
    }

//    @Override
//    public void init() {
//        super.init();
//        final String[] displayProperties ={"firstname" , "lastname" , "phone" , "email"  ,"primaryAddress","created"};
//        setDisplayProperties(displayProperties);
//        setReadOnlyProperties(new String[] { "created" });
//    }
}
