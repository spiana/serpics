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
@Table(name = "multilingual_text")
public class MultilingualText extends MultilingualField<LocalizedText>implements Serializable, Multilingual {
    private static final long serialVersionUID = 7728685826544128982L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "locale")
    @CollectionTable(name = "locale_text_map", joinColumns = @JoinColumn(name = "string_id"))
    private final Map<String,LocalizedText> map = new HashMap<String, LocalizedText>();

    public MultilingualText() {
    }

    public MultilingualText(final String lang, final String text) {
        addText(lang, text);
    }

    

    public Long getId() {
        return Id;
    }

    public void setId(final Long id) {
        Id = id;
    }

	@Override
	public Map<String, LocalizedText> getMap() {
		
		return this.map;
	}

	@Override
	public LocalizedText getLocalizedProperty(String language, String text) {
		return new LocalizedText(language , text);
	}

	 @Override
	    public MultilingualText clone() {
	        final MultilingualText ms = new MultilingualText();
	        for (final LocalizedProperty s : getMap().values())
	            ms.addText(s.getLanguage(), s.getText());
	        return ms;
	    }
}
