package com.serpics.system.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.services.CatalogService;
import com.serpics.membership.services.BaseService;

public class WebappInitializeListener implements ServletContextListener {

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(final ServletContextEvent arg0) {
        final ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0
                .getServletContext());
        final BaseService baseService = applicationContext.getBean(BaseService.class);
        final CatalogService catalogService = applicationContext.getBean(CatalogService.class);
        if (!baseService.isInitialized()) {
            baseService.initIstance();
            final Catalog catalog = new Catalog();
            catalog.setCode("default-catalog");
            catalogService.createCatalog(catalog);
        }
    }

}
