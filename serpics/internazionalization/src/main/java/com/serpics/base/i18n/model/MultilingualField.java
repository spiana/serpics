/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.base.data.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import com.serpics.base.Multilingual;
import com.serpics.core.data.jpa.AbstractEntity;

@MappedSuperclass
public abstract class MultilingualField<T extends LocalizedProperty> extends AbstractEntity implements Serializable , Multilingual{
    private static final long serialVersionUID = 7728685826544128982L;

    public abstract  Map<String,  T> getMap();
     public abstract T getLocalizedProperty(String language, String text);
    
    public void addText(final String language, final String text) {
        getMap().put(language, getLocalizedProperty(language, text));
    }

    public String getText(final java.util.Locale locale) {
    	
        if (getMap().containsKey(locale.getLanguage())) {
            return getMap().get(locale.getLanguage()).getText();
        }
        return null;
    }

    public String getText(final String language) {
        if (getMap().containsKey(language)) {
            return getMap().get(language).getText();
        }
        return null;
    }

    public LocalizedProperty getLocalizedString(final String language) {
        if (getMap().get(language) == null)
        	getMap().put(language, getLocalizedProperty(language, null));
        return getMap().get(language);
    }

}
