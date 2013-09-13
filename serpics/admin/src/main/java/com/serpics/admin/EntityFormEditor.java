/**
 * Copyright 2009-2013 Oy Vaadin Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.admin;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.vaadin.addon.jpacontainer.metadata.EntityClassMetadata;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class EntityFormEditor<T> extends FormLayout implements Button.ClickListener,
        FieldGroupFieldFactory {

    private Item entityItem;

    private Button saveButton;
    private Button cancelButton;
    private Class<T> entityClass;
    
    private transient EntityClassMetadata<T> entityClassMetadata;   
    private transient PropertyList<T> propertyList;
    
	private BeanFieldGroup fieldGroup;
	
	private boolean initialized = false;
	

    public EntityFormEditor(Class<T> entityClass) {
        this.entityClass = entityClass;
        
        
        this.entityClassMetadata = MetadataFactory.getInstance()
                .getEntityClassMetadata(entityClass);
        this.propertyList = new PropertyList<T>(entityClassMetadata);
       
        setSizeUndefined();        
        setCaption(buildCaption());
        
        fieldGroup = new BeanFieldGroup<T>(entityClass);        
        fieldGroup.setFieldFactory(this);        
        
        
		setImmediate(false);
		setWidth("100%");
		setHeight("100%");
		setMargin(true);
		setSpacing(true);
		
		
	    
        
		
    }

    /**
     * @return the caption of the editor window
     */
    private String buildCaption() {
//        return String.format("%s %s", entityItem.getItemProperty("firstName")
//                .getValue(), entityItem.getItemProperty("lastName").getValue());
    	return "pino";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.
     * ClickEvent)
     */
    @Override
    public void buttonClick(ClickEvent event) {
        if (event.getButton() == saveButton) {
            try {
				fieldGroup.commit();
			} catch (CommitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            fireEvent(new EditorSavedEvent(this, entityItem));
        } else if (event.getButton() == cancelButton) {
        	fieldGroup.discard();
        }
//        close();
    }

   

    public void addListener(EditorSavedListener listener) {
        try {
            Method method = EditorSavedListener.class.getDeclaredMethod(
                    "editorSaved", new Class[] { EditorSavedEvent.class });
            addListener(EditorSavedEvent.class, listener, method);
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen
            throw new java.lang.RuntimeException(
                    "Internal error, editor saved method not found");
        }
        
        try {
            Method method = EditorErrorListener.class.getDeclaredMethod(
                    "editorError", new Class[] { EditorErrorEvent.class });
            addListener(EditorErrorEvent.class, listener, method);
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen
            throw new java.lang.RuntimeException(
                    "Internal error, editor saved method not found");
        }
    }

    public void removeListener(EditorSavedListener listener) {
        removeListener(EditorSavedEvent.class, listener);
    }

    public static class EditorSavedEvent extends Component.Event {

        private Item savedItem;

        public EditorSavedEvent(Component source, Item savedItem) {
            super(source);
            this.savedItem = savedItem;
        }

        public Item getSavedItem() {
            return savedItem;
        }
    }
    
    public static class EditorErrorEvent extends Component.Event {

        private Item savedItem;

        public EditorErrorEvent(Component source, Item savedItem) {
            super(source);
            this.savedItem = savedItem;
        }

        public Item getSavedItem() {
            return savedItem;
        }
    }

    public interface EditorSavedListener extends Serializable {
        public void editorSaved(EditorSavedEvent event);
    }
    
    public interface EditorErrorListener extends Serializable {
        public void editorError(EditorSavedEvent event);
    }

	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
		DefaultFieldGroupFieldFactory fa = new DefaultFieldGroupFieldFactory();
		T f = fa.createField(dataType, fieldType);
		if (f instanceof TextField)
			f.setWidth("500px");
		return f;
	}

	public Item getEntityItem() {
		return entityItem;
	}

	public void setEntityItem(Item entityItem) {
		this.entityItem = entityItem;
		fieldGroup.setItemDataSource(entityItem);	
		if (!initialized){
			for (Object pid : entityItem.getItemPropertyIds()){
				Property p = entityItem.getItemProperty(pid);
				if (p.getType() != String.class && p.getType() != Integer.class)
					continue;
				addComponent(fieldGroup.buildAndBind(pid));
				
			}
			
			saveButton = new Button("Save", this);
	        cancelButton = new Button("Cancel", this);
			addComponent(saveButton);
			addComponent(cancelButton);
			
			initialized = true;
		}
		
		
		
			
	}

	

    
    
}
