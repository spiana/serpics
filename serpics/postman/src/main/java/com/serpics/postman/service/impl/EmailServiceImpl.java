package com.serpics.postman.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.core.data.model.Locale;
import com.serpics.postman.data.DataTemplate;
import com.serpics.postman.model.MailState;
import com.serpics.postman.model.MetaDataMail;
import com.serpics.postman.model.TemplateType;
import com.serpics.postman.repositories.MetaDataMailRepository;
import com.serpics.postman.service.EmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private MetaDataMailRepository metaDataMailRepository;
	
	@Resource(name="freemarkerConfiguration")
	Configuration configuration;
	
	@Override
	public void sendMail(TemplateType typeOfTemplate,MetaDataMail mail,DataTemplate dataTemplate) throws Exception{
		
		Assert.notNull(typeOfTemplate,"Cannot send email if the type of template is not indicated");
		Assert.notNull(dataTemplate,"Data for generate template is required. If you don't need data, use NullDataTemplate class");
		Assert.notNull(mail, "Object Mail required");
		
		Template template=null;
			try {
				template = configuration.getTemplate(typeOfTemplate.getUuid());
				LOG.debug("Found 1 for Template type {}",typeOfTemplate.getName());
			} catch (IOException e) {
				LOG.warn("No Template Found for template type {}", typeOfTemplate.getName());
			}

		if(template!=null){
			try {
				
				String bodyOfMail = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataTemplate.getDataForTemplate());
				mail.setBody(bodyOfMail);
				
				LOG.info("save Mail with subject [{}] and type of tempate [{}]",mail.getSubject(),typeOfTemplate.getName() );
				mail.setState(MailState.NEW);
				
				metaDataMailRepository.save(mail);
				
			} catch (IOException|TemplateException e) {
				LOG.error("Error to generate Body of Mail",e);
				throw e;
			} 
			
		}
		
	}
}
