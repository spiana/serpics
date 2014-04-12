package com.serpics.base.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.serpics.core.persistence.jpa.AbstractEntity;

@javax.persistence.Entity
@Table(name = "multilingual_string")
public class MultilingualString extends AbstractEntity {


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "locale")
    @CollectionTable(name = "locale_string_map", joinColumns = @JoinColumn(name = "string_id"))
    private final Map<Locale, LocalizedString> map = new HashMap<Locale, LocalizedString>();

    public MultilingualString() {
    }

    public MultilingualString(final Locale lang, final String text) {
        addText(lang, text);
    }

    public void addText(final Locale locale, final String text) {
        map.put(locale, new LocalizedString(locale, text));
    }

    public String getText(final Locale locale) {
        if (map.containsKey(locale)) {
            return map.get(locale).getText();
        }
        return null;
    }

    public LocalizedString getLocalizedString(final Locale locale) {
        if (map.get(locale) == null)
            map.put(locale, new LocalizedString(locale, null));
        return map.get(locale);
    }

    @Override
    public MultilingualString clone() {
        final MultilingualString ms = new MultilingualString();
        for (final LocalizedString s : map.values())
            ms.addText(s.getLocale(), s.getText());
        return ms;
    }

    public Long getId() {
        return Id;
    }

    public void setId(final Long id) {
        Id = id;
    }

}
