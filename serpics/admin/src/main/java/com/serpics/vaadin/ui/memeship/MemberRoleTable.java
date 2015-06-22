package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.membership.data.model.MembersRole;
import com.serpics.membership.data.model.Role;
import com.serpics.membership.data.model.Store;
import com.serpics.membership.data.model.User;
import com.serpics.membership.data.repositories.RoleRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.jpacontainer.provider.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.filter.Compare;
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
        super(MembersRole.class);
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
                    final ComboBox combo = new ComboBox("role");
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
		_e.addTab(form, "main");
		return _e;
	}

    @Override
    public void init() {
        super.init();
        setParentProperty("membersRoles");
        container.addNestedContainerProperty("role.*");
        setPropertyToShow(new String[] { "role.name" });

        //
        // setTableFieldFactory(new TableFieldFactory() {
        //
        // @Override
        // public Field<?> createField(final Container container, final Object itemId, final Object propertyId,
        // final Component uiContext) {
        // if (propertyId.equals("role")) {
        // final JPAContainer<Role> roles = ServiceContainerFactory.make(Role.class, roleService);
        // final ComboBox combo = new ComboBox("role");
        // combo.setContainerDataSource(roles);
        // combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        // combo.setItemCaptionPropertyId("name");
        // combo.setFilteringMode(FilteringMode.CONTAINS);
        // combo.setImmediate(true);
        // combo.setNullSelectionAllowed(false);
        // combo.setConverter(new SingleSelectConverter(combo));
        // combo.setWidth("80%");
        // // uiContext.bind(combo, "role");
        // return combo;
        // } else {
        // return DefaultFieldFactory.get().createField(container.getItem(itemId), itemId, uiContext);
        // }
        // }
        // });
    }

   

    @Override
    public void attach() {
        if (container != null) {
            container.removeContainerFilters("store");
            addFilter(new Compare.Equal("store", (Store) commerceEngine.getCurrentContext().getStoreRealm()));
        }
        super.attach();
    }

    @Override
    public void save() throws CommitException {
        entityList.commit();
    }

}
