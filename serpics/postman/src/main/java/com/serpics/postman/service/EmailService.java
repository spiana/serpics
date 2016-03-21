package com.serpics.postman.service;

import com.serpics.postman.data.DataTemplate;
import com.serpics.postman.model.MetaDataMail;
import com.serpics.postman.model.TemplateType;

public interface EmailService {

	void sendMail(TemplateType typeOfTemplate, MetaDataMail mail, DataTemplate dataTemplate) throws Exception;

}
