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


@VaadinComponent("userRegEditorComponent")
public class UserRegEditorComponent extends MasterForm<UsersReg> {
    private static final long serialVersionUID = 8474927861483565203L;

  // @Autowired
  // private  transient LocaleRepository localeRepository;

    public UserRegEditorComponent() {
        super(UsersReg.class);
        final String[] p = { "logonid", "password", "alternateEmail", "locale", "lastLogin", "created", "updated",
        "passwordChange" };
        final String[] readonlyProps = { "lastLogin", "created", "updated", "passwordChange" };
        setDisplayProperties(p);
        setReadOnlyProperties(readonlyProps);
   }

//    @Override
//    protected Field<?> createField(final String pid) {
//
//        if (pid.equals("status")) {
//            final ComboBox combo = new ComboBox("status");
//            combo.setNullSelectionAllowed(false);
//            combo.addItem(UserRegStatus.ACTIVE);
//            combo.addItem(UserRegStatus.WAITING);
//            fieldGroup.bind(combo, "status");
//            return combo;
//        } else if (pid.equals("locale")) {
//            final JPAContainer<Locale> locales = ServiceContainerFactory
//                    .make(Locale.class);
//            final ComboBox combo = new ComboBox( I18nUtils.getMessage("usersreg.locale", ""));
//            combo.setContainerDataSource(locales);
//            combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
//            combo.setItemCaptionPropertyId("language");
//            combo.setFilteringMode(FilteringMode.CONTAINS);
//            combo.setImmediate(true);
//            combo.setConverter(new SingleSelectConverter(combo));
//            fieldGroup.bind(combo, "locale");
//            return combo;
//        } else
//            return super.createField(pid);
//    }

}

