package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ctentry database table.
 * 
 */
@Entity
@Table(name = "ctentry")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ctenytry_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Ctentry extends com.serpics.core.persistence.jpa.Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ctentry_id")
    protected Long ctentryId;

    @Column(name = "ctentry_type", nullable = false)
    protected Integer ctentryType;

    @Column(name = "code", nullable = false)
    protected String code;

    protected String field1;

    protected Long field2;

    protected Double field3;

    @Column(name = "url", nullable = false, unique = true)
    protected String url;

    // bi-directional many-to-one association to CtentryAttribute
    @OneToMany(mappedBy = "ctentry", fetch = FetchType.LAZY)
    protected Set<CtentryAttribute> ctentryAttributes;

    // bi-directional many-to-one association to CtentryDescr
    @OneToMany(mappedBy = "ctentry", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = CtentryDescr.class)
    protected Set<CtentryDescr> ctentryDescrs = new HashSet<CtentryDescr>(0);

    // bi-directional many-to-one association to Media
    @OneToMany(mappedBy = "ctentry", fetch = FetchType.LAZY)
    protected Set<Media> medias;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    public Long getCtentryId() {
        return this.ctentryId;
    }

    public void setCtentryId(final Long ctentryId) {
        this.ctentryId = ctentryId;
    }

    public Integer getCtentryType() {
        return this.ctentryType;
    }

    public void setCtentryType(final Integer ctentryType) {
        this.ctentryType = ctentryType;
    }

    public String getField1() {
        return this.field1;
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public Long getField2() {
        return this.field2;
    }

    public void setField2(final Long field2) {
        this.field2 = field2;
    }

    public Double getField3() {
        return this.field3;
    }

    public void setField3(final Double field3) {
        this.field3 = field3;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Set<CtentryAttribute> getCtentryAttributes() {
        return this.ctentryAttributes;
    }

    public void setCtentryAttributes(final Set<CtentryAttribute> ctentryAttributes) {
        this.ctentryAttributes = ctentryAttributes;
    }

    public Set<CtentryDescr> getCtentryDescrs() {
        return this.ctentryDescrs;
    }

    public void setCtentryDescrs(final Set<CtentryDescr> ctentryDescrs) {
        this.ctentryDescrs = ctentryDescrs;
    }

    public Set<Media> getMedias() {
        return this.medias;
    }

    public void setMedias(final Set<Media> medias) {
        this.medias = medias;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }


    @PrePersist
    @Override
    public void beforePersist() {
        this.created = new Date();
        super.beforePersist();
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}