package com.serpics.base.data.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.serpics.core.data.jpa.AbstractEntity;

@Entity
@Table(name = "multilingual_text")
public class MultilingualText extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 7728685826544128982L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "locale")
    @CollectionTable(name = "locale_text_map", joinColumns = @JoinColumn(name = "string_id"))
    private final Map<String, LocalizedString> map = new HashMap<String, LocalizedString>();

    public MultilingualText() {
    }

    public MultilingualText(final String lang, final String text) {
        addText(lang, text);
    }

    public void addText(final String language, final String text) {
        map.put(language, new LocalizedString(language, text));
    }

    public String getText(final java.util.Locale locale) {
    	
        if (map.containsKey(locale.getLanguage())) {
            return map.get(locale.getLanguage()).getText();
        }
        return null;
    }

    public String getText(final String language) {
        if (map.containsKey(language)) {
            return map.get(language).getText();
        }
        return null;
    }

    public LocalizedString getLocalizedString(final String language) {
        if (map.get(language) == null)
            map.put(language, new LocalizedString(language, null));
        return map.get(language);
    }

    @Override
    public MultilingualText clone() {
        final MultilingualText ms = new MultilingualText();
        for (final LocalizedString s : map.values())
            ms.addText(s.getLanguage(), s.getText());
        return ms;
    }

    public Long getId() {
        return Id;
    }

    public void setId(final Long id) {
        Id = id;
    }

    public Map<String, LocalizedString> getMap() {
        return map;
    }

}
