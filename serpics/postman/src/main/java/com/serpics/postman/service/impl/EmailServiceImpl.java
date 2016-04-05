package com.serpics.postman.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.postman.data.DataTemplate;
import com.serpics.postman.freemarker.DatabaseTemplateStoreLoader;
import com.serpics.postman.model.MailState;
import com.serpics.postman.model.MetaDataMail;
import com.serpics.postman.model.TemplateStore;
import com.serpics.postman.model.TemplateType;
import com.serpics.postman.repositories.MetaDataMailRepository;
import com.serpics.postman.service.EmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private static final java.util.Locale DEFAULT_LANGUAGE = java.util.Locale.ENGLISH;
	
	@Autowired
	private MetaDataMailRepository metaDataMailRepository;
	
	@Autowired
	private DatabaseTemplateStoreLoader templateStoreLoader;
	
	@Resource(name="freemarkerConfiguration")
	Configuration configuration;
	
	@Autowired
	CommerceEngine commerceEngine;
	
	@Override
	public void sendMail(TemplateType typeOfTemplate,MetaDataMail mail,DataTemplate dataTemplate) throws Exception{
		
		Assert.notNull(typeOfTemplate,"Cannot send email if the type of template is not indicated");
		Assert.notNull(dataTemplate,"Data for generate template is required. If you don't need data, use NullDataTemplate class");
		Assert.notNull(mail, "Object Mail required");
		
		TemplateStore template = null;
		CommerceSessionContext commerceSession = commerceEngine.getCurrentContext();
		
		String language = StringUtils.defaultString(commerceSession.getLocale().getLanguage(), DEFAULT_LANGUAGE.getLanguage());
		
			try {
				template = templateStoreLoader.findTemplateStore(typeOfTemplate, language);
				LOG.debug("Found 1 for Template type {} ",typeOfTemplate.getName());
			} catch (IOException e) {
				LOG.warn("No Template Found for template type {}", typeOfTemplate.getName());
			}
			
		if(template!=null){
			try {
				Template templateFreemarker = new Template(template.getName(), template.getTemplateMail().getText(language), configuration);
				
				String bodyOfMail = FreeMarkerTemplateUtils.processTemplateIntoString(templateFreemarker, dataTemplate.getDataForTemplate());
				mail.setBody(bodyOfMail);
				
				templateFreemarker = new Template(template.getName(), template.getTemplateSubjectMail().getText(language), configuration);
				
				String subjectOfMail = FreeMarkerTemplateUtils.processTemplateIntoString(templateFreemarker, dataTemplate.getDataForTemplate());
				mail.setSubject(subjectOfMail);
				
				LOG.info("save Mail with subject [{}] and type of tempate [{}]",mail.getSubject(),template.getName() );
				mail.setState(MailState.NEW);
				
				metaDataMailRepository.save(mail);
				
			} catch (IOException|TemplateException e) {
				LOG.error("Error to generate Body of Mail",e);
				throw e;
			} 
			
		}
		
	}
}
