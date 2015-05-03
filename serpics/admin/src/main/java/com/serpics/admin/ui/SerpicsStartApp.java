package com.serpics.admin.ui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.vaadin.ui.EntityComponent;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Component
@Scope("prototype")
public class SerpicsStartApp extends UI {
    private static final long serialVersionUID = -5966946454650068735L;

    @Autowired
    private transient CommerceEngine commerceEngine;

    private final TabSheet leftContentPanel = new TabSheet();
    private final Map<String, EntityComponent> activeComponent = new HashMap<String, EntityComponent>(0);

    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();
        setContent(layout);
        final Label title = new Label("Serpics Admin Console");
        title.setWidth("100%");
        title.setHeight("30px");
        title.setStyleName("Apptitle");
        layout.addComponent(title);

        final HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();

        final com.serpics.base.data.model.Locale locale = (com.serpics.base.data.model.Locale) commerceEngine.getCurrentContext().getLocale();
        
        Locale _locale = new Locale("it", "IT");
        if (locale != null) {
            _locale = new Locale(locale.getLanguage(), locale.getCountry());
        }

        getSession().setLocale(_locale);

        final Tree menu = new Tree();
        menu.setWidth("150px");
        populateMenu(menu);
        content.addComponent(menu);


        menu.addItemClickListener(new ItemClickListener() {

            @Override
            public void itemClick(final ItemClickEvent event) {
                final String itemid = (String) event.getItemId();
                if (itemid == "users") {
                    addComponent("userTableEditor", "user");
                } else if (itemid == "groups") {
                    addComponent("membergroupTableEditor", "groups");
                } else if (itemid == "relation") {
                    addComponent("membergroupRelTable", "user2grouprelation");
                } else if (itemid == "userReg") {
                    addComponent("userRegTableEditor", "userReg");
                } else if (itemid == "category") {
                    addComponent("categoryTable", "category");
                } else if (itemid == "product") {
                    addComponent("productTable", "product");
                } else if (itemid == "country") {
                    addComponent("countryTable", "country");
                }
            }
        });

        leftContentPanel.setSizeFull();

        content.addComponent(leftContentPanel);
        content.setExpandRatio(leftContentPanel, 1);

        layout.addComponent(content);
        layout.setExpandRatio(content, 1);

    }

    private void populateMenu(final Tree menu) {
        menu.addItem("admin");
        menu.addItem("users");
        menu.setParent("users", "admin");
        menu.setChildrenAllowed("users", false);
        menu.addItem("groups");
        menu.setParent("groups", "admin");
        menu.setChildrenAllowed("groups", false);

        menu.addItem("userReg");
        menu.setParent("userReg", "admin");
        menu.setChildrenAllowed("userReg", false);

        menu.addItem("relation");
        menu.setParent("relation", "admin");
        menu.setChildrenAllowed("relation", false);

        menu.addItem("country");
        menu.setParent("country", "admin");
        menu.setChildrenAllowed("country", false);
        
        menu.addItem("catalog");
        menu.addItem("category");
        menu.setParent("category", "catalog");
        menu.setChildrenAllowed("category", false);

        menu.addItem("product");
        menu.setParent("product", "catalog");
        menu.setChildrenAllowed("product", false);

    }

    private void addComponent(final String id, final String caption) {
        final EntityComponent _component = getComponent(id);

        final Tab t = leftContentPanel.getTab(_component);
        if (t == null) {
            leftContentPanel.addTab(_component, caption);
            leftContentPanel.getTab(_component).setClosable(true);
        }
        leftContentPanel.setSelectedTab(_component);
    }
    private EntityComponent getComponent(final String name) {

        EntityComponent _component = activeComponent.get(name);
        if (_component == null) {
            _component = (EntityComponent) commerceEngine.getApplicationContext().getBean(name);
            // _component.init();

            activeComponent.put(name, _component);
        }
        return _component;

    }

}
