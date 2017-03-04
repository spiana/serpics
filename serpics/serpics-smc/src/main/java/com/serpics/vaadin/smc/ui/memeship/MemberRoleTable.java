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

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.RoleRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

@VaadinComponent(value = "memberRoleTable")
public class MemberRoleTable extends MasterDetailTable<MembersRole, User> {
    private static final long serialVersionUID = -3391935047933834592L;

    @Autowired
    private transient CommerceEngine commerceEngine;

    @Autowired
    private RoleRepository roleRepository;
   
    public MemberRoleTable() {
        super(MembersRole.class , "membersRoles" , "member");
    }

	@Override
	public EntityFormWindow<MembersRole> buildEntityWindow() {
		EntityFormWindow<MembersRole> _e = new EntityFormWindow<MembersRole>();
		final MasterForm<MembersRole> form = new MasterForm<MembersRole>(MembersRole.class) {
            private static final long serialVersionUID = 1L;

            @Override
            public void init() {
                super.init();
                setDisplayProperties(new String[] { "role" });
            }

            @Override
            protected Field<?> createField(final String pid) {

                if (pid.equals("role")) {
                    final JPAContainer<Role> roles = ServiceContainerFactory.make(Role.class);
                    final ComboBox combo = new ComboBox(I18nUtils.getMessage("membersrole.role", ""));
                    combo.setContainerDataSource(roles);
                    combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
                    combo.setItemCaptionPropertyId("name");
                    combo.setFilteringMode(FilteringMode.CONTAINS);
                    combo.setImmediate(true);
                    combo.setNullSelectionAllowed(false);
                    combo.setConverter(new SingleSelectConverter(combo));
                    combo.setWidth("80%");
                    fieldGroup.bind(combo, "role");
                    return combo;

                }

                return super.createField(pid);
            }
        };
		//_e.addTab(form, "main");
		_e.addTab(form,  I18nUtils.getMessage("membersrole.main",""));
		 
		return _e;
	}

    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "role.name" });
    }

}
