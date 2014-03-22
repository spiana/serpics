package com.serpics.catalog.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ctentry_descr database table.
 * 
 */
@Embeddable
public class CtentryDescrPK implements Serializable {

    // default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="ctentry_id")
    private Long ctentryId;

    @Column(name="locale_id")
    private Long localeId;

    public CtentryDescrPK() {
    }

    public CtentryDescrPK(final Long ctentryId, final Long localeId) {
        super();
        this.ctentryId = ctentryId;
        this.localeId = localeId;
    }

    public Long getCtentryId() {
        return ctentryId;
    }

    public void setCtentryId(final Long ctentryId) {
        this.ctentryId = ctentryId;
    }

    public Long getLocaleId() {
        return this.localeId;
    }
    public void setLocaleId(final Long localeId) {
        this.localeId = localeId;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CtentryDescrPK)) {
            return false;
        }
        final CtentryDescrPK castOther = (CtentryDescrPK)other;
        return 
                this.ctentryId.equals(castOther.ctentryId)
                && this.localeId.equals(castOther.localeId);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ctentryId.hashCode();
        hash = hash * prime + this.localeId.hashCode();

        return hash;
    }
}