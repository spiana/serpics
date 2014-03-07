package com.serpics.vaadin.ui.memeship;

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.admin.SerpicsContainerFactory;
import com.serpics.core.CommerceEngine;
import com.serpics.membership.persistence.MembersRole;
import com.serpics.membership.persistence.MembersRolePK;
import com.serpics.membership.persistence.Role;
import com.serpics.membership.persistence.Store;
import com.serpics.membership.persistence.User;
import com.serpics.membership.services.MemberRoleService;
import com.serpics.membership.services.RoleService;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityTableChild;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

@VaadinComponent(value = "memberRoleTable")
public class MemberRoleTable extends EntityTableChild<MembersRole, User> {
    private static final long serialVersionUID = -3391935047933834592L;

    @Autowired
    CommerceEngine commerceEngine;

    @Autowired
    private MemberRoleService memberRoleService;

    @Autowired
    private RoleService roleService;

    public MemberRoleTable() {
        super(MembersRole.class);
    }

    @Override
    public void init() {
        setService(memberRoleService);
        final EntityForm<MembersRole> form = new EntityForm<MembersRole>(MembersRole.class) {
            private static final long serialVersionUID = 1L;

            @Override
            public void init() {
                setDisplayProperties(new String[] { "role" });
                super.init();
            }

            @Override
            public void save() throws CommitException {
                fieldGroup.commit();

                if (!entityItem.isPersistent()) {
                    if (super.entityItem.getItemId() == null) {
                        final MembersRole m = entityItem.getEntity();
                        final MembersRolePK pk = new MembersRolePK(m.getRole().getRoleId(),
                                m.getMember().getMemberId(), commerceEngine.getCurrentContext().getStoreId());
                        m.setId(pk);
                        entityItem.getContainer().addEntity(m);
                    }
                }

                super.save();
            }

            @Override
            protected Field<?> createField(final String pid) {

                if (pid.equals("role")) {
                    final SerpicsPersistentContainer<Role> roles = SerpicsContainerFactory
                            .make(Role.class, roleService);
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
        this.editorWindow.addTab(form, "main");

        cont.addNestedContainerProperty("role.*");
        setPropertyToShow(new String[] { "role.name" });
        super.init();
    }

    @Override
    public EntityItem<MembersRole> createEntityItem() {
        final MembersRole m = new MembersRole();
        m.setMember(parent.getEntity());
        return cont.createEntityItem(m);
    }

    @Override
    public void setParentEntity(final EntityItem<User> parent) {
        removeAllFilter();
        addFilter(new Compare.Equal("member", parent.getEntity()));
        addFilter(new Compare.Equal("store", (Store) commerceEngine.getCurrentContext().getStoreRealm()));
        super.setParentEntity(parent);
    }

}
