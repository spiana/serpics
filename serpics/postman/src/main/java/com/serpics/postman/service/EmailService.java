package com.serpics.postman.service;

import com.serpics.postman.model.MetaDataMail;
import com.serpics.postman.model.TemplateType;

public interface EmailService {

	void sendMail(TemplateType typeOfTemplate, MetaDataMail mail, Object dataTemplate) throws Exception;

}
