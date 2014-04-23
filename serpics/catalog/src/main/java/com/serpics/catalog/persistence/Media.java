package com.serpics.catalog.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.serpics.base.persistence.MultilingualString;


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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "description_string_id")
    private final MultilingualString description = new MultilingualString();

    public Media() {
    }

    public Long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(final Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public short getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(final short mediaType) {
        this.mediaType = mediaType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getSequence() {
        return this.sequence;
    }

    public void setSequence(final double sequence) {
        this.sequence = sequence;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(final String src) {
        this.src = src;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(final Timestamp updated) {
        this.updated = updated;
    }

    public Ctentry getCtentry() {
        return this.ctentry;
    }

    public void setCtentry(final Ctentry ctentry) {
        this.ctentry = ctentry;
    }

    public MultilingualString getDescription() {
        return description;
    }



}