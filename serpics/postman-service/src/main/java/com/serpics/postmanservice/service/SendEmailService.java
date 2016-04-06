package com.serpics.postmanservice.service;

import org.springframework.data.domain.Page;

import com.serpics.postman.model.MetaDataMail;

public interface SendEmailService {

	void sendMetaDataMail(MetaDataMail mailBean);

	Page<MetaDataMail> getListOfMailToProcess(int numberOfMail);

}
