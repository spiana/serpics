package com.serpics.membership.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.membership.facade.data.UserData;
import com.serpics.postman.model.MetaDataMail;
import com.serpics.postman.model.TemplateType;
import com.serpics.postman.repositories.TemplateTypeRepository;
import com.serpics.postman.service.EmailService;
import com.serpics.stereotype.StoreFacade;

@StoreFacade("emailFacade")
public class EmailFacadeImpl implements EmailFacade {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmailService emailService; 
	
	@Autowired
	TemplateTypeRepository templateTypeRepository;
	
	@Override
	public void sendEmailRegister(UserData userData){
		//Retrieve the tyeoe of template correct
		TemplateType templateType= templateTypeRepository.getTemplateTypeByName("Registrazione Utente");
		
		//Retrieve the addre email correct (FROM,TO,CC);
		
		MetaDataMail mail =new MetaDataMail();
		
		mail.setMailFrom("serpics@serpicsframework.com");
		mail.setMailTo(userData.getEmail());
		//Eventually
//		mail.setMailCC("");
		
		try {
			emailService.sendMail(templateType, mail, templateType);
		} catch (Exception e) {
			logger.error("impossible to send email on Register User event",e);
		}
	}
}
