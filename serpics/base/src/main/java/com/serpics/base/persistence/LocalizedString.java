package com.serpics.base.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class LocalizedString {
    @JoinColumn(name = "locale_id")
    private String language;

    @Column(name = "text")
    private String text;

    public LocalizedString() {
    }

    public LocalizedString(final String language, final String text) {
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
