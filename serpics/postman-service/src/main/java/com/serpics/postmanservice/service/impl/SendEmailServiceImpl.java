package com.serpics.postmanservice.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.serpics.base.data.model.Media;
import com.serpics.base.utils.MediaStoreUtil;
import com.serpics.postman.model.MailState;
import com.serpics.postman.model.MetaDataMail;
import com.serpics.postman.repositories.MetaDataMailRepository;
import com.serpics.postmanservice.service.SendEmailService;

@Service("sendEmailService")
public class SendEmailServiceImpl implements SendEmailService {

	Logger logger = LoggerFactory.getLogger(SendEmailServiceImpl.class);

	@Autowired
	private MetaDataMailRepository metaDataMailRepository;

	@Autowired
	private MediaStoreUtil mediaStoreUtil;
	
	@Autowired
	private JavaMailSender javaMail;
	
	@Override
	public Page<MetaDataMail> getListOfMailToProcess(int numberOfMail) {

		return metaDataMailRepository.findMailToSend(Arrays.asList(MailState.NEW, MailState.RETRY), new PageRequest(0, numberOfMail, Sort.Direction.ASC, "created"));

	}

	@Override
	public void sendMetaDataMail(MetaDataMail mailBean){
		
		try{
			
			tryToSendMail(mailBean);
			mailBean.setState(MailState.SEND);
			mailBean.setError(StringUtils.EMPTY);
			
		}catch(IOException|MessagingException e){
			logger.warn("Mail for Store {} and subject {} not send",mailBean.getStore().getName(),mailBean.getSubject());
			mailBean.setState(MailState.ERROR);
			mailBean.setError(e.getMessage());
		}
	
		metaDataMailRepository.saveAndFlush(mailBean);
		
	}
	
	protected void tryToSendMail(MetaDataMail mailBean) throws IOException, MessagingException{
		
		logger.debug("sendmail called");
		
		if (StringUtils.isBlank(mailBean.getMailTo()) || mailBean.getBody() == null ||
				StringUtils.isBlank(mailBean.getSubject())) {
				throw new IllegalArgumentException("Mandatory paramenters are not set. addressReceiver:" +
					mailBean.getMailTo() + ", body:" + mailBean.getBody() + ", subject:" + mailBean.getSubject());
			}
		
		MimeMessage mimeMessage = javaMail.createMimeMessage();
		
		try {
			// Mittente
			if (!StringUtils.isBlank(mailBean.getMailFrom())) {
			
					mimeMessage.setFrom(new InternetAddress(mailBean.getMailFrom()));
			}
			
			String[] recipients = StringUtils.split(mailBean.getMailTo(), ";");
			InternetAddress[] recipientsAsAddresses = stringsToAddresses(recipients);
			mimeMessage.setRecipients(RecipientType.TO, recipientsAsAddresses);
			
			// destinatari - CC - facoltativi
			recipients = StringUtils.split(mailBean.getMailCC(), ";");
			if (recipients != null && recipients.length > 0) {
				recipientsAsAddresses = stringsToAddresses(recipients);
				mimeMessage.setRecipients(RecipientType.CC, recipientsAsAddresses);
			}
			// destinatari - CCN - facoltativi
//			recipients = StringUtils.split(mailBean.getMailCcn(), ";");
//			if (recipients != null && recipients.length > 0) {
//				recipientsAsAddresses = stringsToAddresses(recipients);
//				mimeMessage.setRecipients(RecipientType.BCC, recipientsAsAddresses);
//			}
			
			// Subject
			mimeMessage.setSubject(mailBean.getSubject());
						
			mimeMessage.setContent(mailBean.getBody(), "text/html");
			
			// allegati
			if(mailBean.getAttachment()!=null){
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart(); 
				
				mbp.setText(mailBean.getBody());
				mp.addBodyPart(mbp);
	
//				for(Media media in mailBean.getAttachments()) {
				Media media = mailBean.getAttachment();
				mp.addBodyPart(setBodyPartAttachment(mediaStoreUtil.getLocalMedia(media), media.getName(),media.getContentType()));
//				}
				
			    // add the Multipart to the message
				mimeMessage.setContent(mp);
			}
		
			logger.debug("send mail "+mailBean);
			
			mimeMessage.setSentDate(Calendar.getInstance().getTime());
			javaMail.send(mimeMessage);
			
			logger.debug("sendmail end");
		} catch (AddressException e) {
			logger.error("errore in SendMail",e);
			throw e;
		} catch (MessagingException e) {
			logger.error("errore in SendMail",e);
			throw e;
		} catch (IOException e) {
			logger.error("errore in SendMail",e);
			throw e;
		} catch (MailException e){
			logger.error("errore in SendMail",e);
			throw e;
		}
	}

	private InternetAddress[] stringsToAddresses(String[] recipients) throws AddressException {

		InternetAddress[] recipientsAsAddresses = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			recipientsAsAddresses[i] = new InternetAddress(recipients[i], false);
		}
		return recipientsAsAddresses;
	}

	private MimeBodyPart setBodyPartAttachment(byte[] attachement, String nameAttachement, String contentType)
			throws MessagingException, IOException {

		if (attachement != null && !StringUtils.isBlank(nameAttachement)) {
			if (StringUtils.isBlank(contentType)) {
				contentType = "application/pdf";
			}

			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setFileName(nameAttachement);
			mbp.addHeader("Content-Type", contentType);
			ByteArrayDataSource ds = new ByteArrayDataSource(attachement, "application/vnd.ms-excel");
			mbp.setDataHandler(new DataHandler(ds));
			return mbp;
		}
		return null;
	}

}
