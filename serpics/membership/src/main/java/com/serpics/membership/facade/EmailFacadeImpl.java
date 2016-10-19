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
