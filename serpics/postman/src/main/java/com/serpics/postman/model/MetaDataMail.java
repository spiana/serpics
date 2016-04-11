package com.serpics.postman.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.serpics.base.data.model.AbstractStoreEntity;
import com.serpics.base.data.model.Media;

@Table(name="metadata_mail")
@Entity
public class MetaDataMail extends AbstractStoreEntity {
	
	private static final long serialVersionUID = 7565982329202491380L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "metadatamail_id", unique = true, nullable = false)
	private Long id;
	
	@JoinColumn(name="media_id",nullable=true)
	private Media attachment;
	
	@Column(name="mail_from",nullable = false)
	private String mailFrom;
	
	@Column(name="mail_to",nullable = false)
	private String mailTo;
	
	@Column(name="mail_cc",nullable = true)
	private String mailCC;
	
	@Column(name="mail_subject",nullable = false)
	private String subject;
	
	@Lob
	@Column(name="mail_body",nullable=false)
	private String body;
	
	@Column(name="mail_state",nullable = false )
	@Enumerated
	private MailState state;
	
	@Column(name="mail_error",nullable = true)
	private String error;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Media getAttachment() {
		return attachment;
	}

	public void setAttachment(Media attachment) {
		this.attachment = attachment;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailCC() {
		return mailCC;
	}

	public void setMailCC(String mailCC) {
		this.mailCC = mailCC;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MailState getState() {
		return state;
	}

	public void setState(MailState state) {
		this.state = state;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MetaDataMail [id=");
		builder.append(id);
		builder.append(", attachment=");
		if(attachment!=null){
			builder.append(attachment.getName());
			builder.append("{ path=");
			builder.append(attachment.getSourcePath());
			builder.append(" } ");
		}else{
			builder.append("No Attachment");
		}
		builder.append(", mailFrom=");
		builder.append(mailFrom);
		builder.append(", mailTo=");
		builder.append(mailTo);
		builder.append(", mailCC=");
		builder.append(mailCC);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
	
}
