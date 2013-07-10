package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@Table(name="media")
public class Media implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="media_id")
	private Long mediaId;

	@Column(name="content_type")
	private String contentType;

	@Column(name="media_type")
	private short mediaType;

	private String name;

	private double sequence;

	private String src;

	private Timestamp updated;

	//bi-directional many-to-one association to Ctentry
    @ManyToOne
	@JoinColumn(name="ctentry_id")
	private Ctentry ctentry;

	//bi-directional many-to-one association to MediaDescr
	@OneToMany(mappedBy="media")
	private Set<MediaDescr> mediaDescrs;

    public Media() {
    }

	public Long getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public short getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(short mediaType) {
		this.mediaType = mediaType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSequence() {
		return this.sequence;
	}

	public void setSequence(double sequence) {
		this.sequence = sequence;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Ctentry getCtentry() {
		return this.ctentry;
	}

	public void setCtentry(Ctentry ctentry) {
		this.ctentry = ctentry;
	}
	
	public Set<MediaDescr> getMediaDescrs() {
		return this.mediaDescrs;
	}

	public void setMediaDescrs(Set<MediaDescr> mediaDescrs) {
		this.mediaDescrs = mediaDescrs;
	}
	
}