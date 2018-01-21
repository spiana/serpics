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
package com.serpics.system.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.commerce.CommerceEngine;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.catalog.services.CatalogService;
import com.serpics.i18n.data.model.Locale;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.membership.services.BaseService;

public class CommerceRequestFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CommerceRequestFilter.class);

	@Resource
	BaseService baseService;
	
    @Autowired
    CommerceEngine ce;
    
    @Resource
    CatalogService catalogService;

    @Resource
    LocaleRepository localeRepository;
    
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp,
            final FilterChain chain) throws IOException, ServletException {
    	
    	 if (logger.isDebugEnabled())
             logger.debug("CommerceSessionFilter called");

         if (!baseService.isInitialized()){
         	logger.warn("serpics is not initialized !");
         	baseService.initIstance();
         }
         
         final HttpServletRequest httpReq = (HttpServletRequest) req;
         final String id = (String) httpReq.getHeader(WebCostant.SSID_SERPICS_TOKEN);
         
         CommerceSessionContext context = null;
         
         if (id != null) {
             context = ce.bind(id);
             if(context.getLocale()==null){
            	 Locale locale = localeRepository.findByLanguage(httpReq.getLocale().getLanguage());
            	 if(locale==null){
            		 locale = localeRepository.findByLanguage("en");
            	 }
            	 
            	 context.setLocale(locale);
             }
             if (context.getCatalog() == null){
            	 catalogService.initialize();
             }
         }

         chain.doFilter(req, resp);
         ce.unbind(); // unbind from commerce Session
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
