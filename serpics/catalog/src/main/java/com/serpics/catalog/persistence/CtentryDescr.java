package com.serpics.catalog.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.serpics.base.persistence.Locale;

/**
 * The persistent class for the ctentry_descr database table.
 * 
 */
@Entity
@Table(name="ctentry_descr" )
public class CtentryDescr extends com.serpics.core.persistence.jpa.Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CtentryDescrPK id;

    @Lob()
    private String description;

    @Column(name="meta_description")
    private String metaDescription;

    @Column(name="meta_keyword")
    private String metaKeyword;

    private String name;

    //bi-directional many-to-one association to Ctentry
    @ManyToOne
    @JoinColumn(name="ctentry_id" , insertable=false , updatable=false)
    private Ctentry ctentry;

    @ManyToOne
    @JoinColumn(name = "locale_id", insertable = false, updatable = false)
    private Locale locale;

    public CtentryDescr() {
    }

    public CtentryDescrPK getId() {
        return this.id;
    }

    public void setId(final CtentryDescrPK id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getMetaDescription() {
        return this.metaDescription;
    }

    public void setMetaDescription(final String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeyword() {
        return this.metaKeyword;
    }

    public void setMetaKeyword(final String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Ctentry getCtentry() {
        return ctentry;
    }

    public void setCtentry(final Ctentry ctentry) {
        this.ctentry = ctentry;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

}