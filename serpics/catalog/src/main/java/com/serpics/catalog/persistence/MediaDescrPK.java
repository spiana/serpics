package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the media_descr database table.
 * 
 */
@Embeddable
public class MediaDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="locale_id")
	private Long localeId;

	@Column(name="media_id")
	private Long mediaId;

    public MediaDescrPK() {
    }
	public Long getLocaleId() {
		return this.localeId;
	}
	public void setLocaleId(Long localeId) {
		this.localeId = localeId;
	}
	public Long getMediaId() {
		return this.mediaId;
	}
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MediaDescrPK)) {
			return false;
		}
		MediaDescrPK castOther = (MediaDescrPK)other;
		return 
			this.localeId.equals(castOther.localeId)
			&& this.mediaId.equals(castOther.mediaId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.localeId.hashCode();
		hash = hash * prime + this.mediaId.hashCode();
		
		return hash;
    }
}