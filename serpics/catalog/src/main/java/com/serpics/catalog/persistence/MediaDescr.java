package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the media_descr database table.
 * 
 */
@Entity
@Table(name="media_descr")
public class MediaDescr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MediaDescrPK id;

	private String alt;

	private String description;

	private String title;

	private Timestamp updated;

	//bi-directional many-to-one association to Media
    @ManyToOne
	@JoinColumn(name="media_id" , insertable=false , updatable=false)
	private Media media;

    public MediaDescr() {
    }

	public MediaDescrPK getId() {
		return this.id;
	}

	public void setId(MediaDescrPK id) {
		this.id = id;
	}
	
	public String getAlt() {
		return this.alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
}