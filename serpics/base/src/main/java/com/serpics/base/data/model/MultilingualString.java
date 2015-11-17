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

import com.serpics.base.Multilingual;

@Entity
@Table(name = "multilingual_string")
public class MultilingualString extends MultilingualField<LocalizedString> implements Serializable , Multilingual {
    private static final long serialVersionUID = 7728685826544128982L;

   
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    Long Id;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "locale")
    @CollectionTable(name = "locale_string_map", joinColumns = @JoinColumn(name = "string_id"))
    private final Map<String, LocalizedString> map = new HashMap<String, LocalizedString>();

    public MultilingualString() {
    }
    public MultilingualString(final String lang, final String text) {
        addText(lang, text);
    }

    public Long getId() {
        return Id;
    }

    public void setId(final Long id) {
        Id = id;
    }

    @Override
    public MultilingualString clone() {
        final MultilingualString ms = new MultilingualString();
        for (final LocalizedProperty s : getMap().values())
            ms.addText(s.getLanguage(), s.getText());
        return ms;
    }
	@Override
	public Map<String, LocalizedString> getMap() {
		return this.map;
	}
	@Override
	public LocalizedString getLocalizedProperty(String language, String text) {
		return new LocalizedString(language, text);
	}
	
}
