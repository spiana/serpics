package com.serpics.jaxrs;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.SerpicsException;
@Path("connect/")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Resource
	CommerceEngine commerceEngine;

	@Resource
	CatalogService catalogService;
	
	@Autowired
	LocaleRepository localeRepository;
	
	
    /**
     * This method connects session to a store.
     * @summary  Method: connect(String store)
     * @param store The store connected
     * @return string A string into body response with the Ssid
     * 
     */
	@Override
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{store}")
	public String connect(@PathParam("store") String store) {
		
		if (store == null){
			store = "default-store";
		}
		try {
			CommerceSessionContext context= commerceEngine.connect(store);
			// Initialize the default-catalog
			catalogService.initialize();
			context.setLocale(localeRepository.findByLanguage("it"));
			
			return context.getSessionId();
		} catch (SerpicsException e) {
			// TODO Auto-generated catch block
			LOG.error("Error On Connect/{Store}", e);
		}
		return null;
	}

}
