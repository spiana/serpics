package com.serpics.base.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

@Embeddable
public class LocalizedText implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "locale_id")
    private String language;

    @Column(name = "text")
    @Lob
    private String text;

    public LocalizedText() {
    }

    public LocalizedText(final String language, final String text) {
        super();
        this.language = language;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }
}
