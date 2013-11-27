package com.serpics.catalog.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the feature_descr database table.
 * 
 */
@Embeddable
public class FeatureDescrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="feature_id")
	private Long feautureId;

	@Column(name="locale_id")
	private Long localeId;

    public FeatureDescrPK() {
    }
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FeatureDescrPK)) {
			return false;
		}
		FeatureDescrPK castOther = (FeatureDescrPK)other;
		return 
			this.feautureId.equals(castOther.feautureId)
			&& this.localeId.equals(castOther.localeId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.feautureId.hashCode();
		hash = hash * prime + this.localeId.hashCode();
		
		return hash;
    }
}