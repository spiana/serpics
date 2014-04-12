package com.serpics.base.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class LocalizedString {
    @JoinColumn(name = "locale_id")
    private Locale locale;

    @Column(name = "text")
    private String text;

    public LocalizedString() {
    }

    public LocalizedString(final Locale locale, final String text) {
        super();
        this.locale = locale;
        this.text = text;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
