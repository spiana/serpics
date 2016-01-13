package com.serpics.base.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.serpics.base.MediaSupportType;
import com.serpics.core.data.jpa.AbstractEntity;

@Entity()
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"media_id", "format"}))
public class MediaSupport extends AbstractEntity{
	private static final long serialVersionUID = 471728478792958913L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mediasupport_id")
	private Long id;
	
	@Column(nullable=false)
	@NotNull
	@Pattern(regexp="[A-Za-z0-9-_]")
	private String name;
	
	@Column(nullable=false)
	@NotNull
	//@Pattern(regexp="[A-Z][a-z][0-9]-")
	private String format;

	@ManyToOne(optional=false)
	@JoinColumn(name="media_id")
	private Media media;
	
	@Enumerated
	private MediaSupportType type ;
	
	private String src;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public MediaSupportType getType() {
		return type;
	}

	public void setType(MediaSupportType type) {
		this.type = type;
	}
	
}