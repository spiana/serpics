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
package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.EntityComponent.MasterTableComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;

import de.steinwedel.messagebox.MessageBox;





public class EntityFormWindow<T> extends Window implements Handler {
    private static final long serialVersionUID = 2590755708760150150L;

    private final TabSheet tabSheet = new TabSheet();
    static final ShortcutAction esc = new ShortcutAction("Close window", ShortcutAction.KeyCode.ESCAPE, null);
    static final ShortcutAction[] actions = new ShortcutAction[] { esc };

    private boolean readOnly = true;
    private boolean newItem = true;

    private final List<EntityComponent<?>> componentList = new ArrayList<EntityComponent<?>>(0);

    private Button saveButton;
    private Button cancelButton;
    private Button createButton;
    
    private boolean  singletab = true ;
   private transient EntityItem<T> item;
  
    private transient VerticalLayout vl = new VerticalLayout();
    
    public EntityFormWindow() throws SecurityException {
    	setCaption(I18nUtils.getMessage("editWindow", "Edit Window"));
    	init();
    }

    public EntityFormWindow(String caption) throws SecurityException {
       		setCaption(I18nUtils.getMessage(caption, caption));
       		init();
    }
    
   /**
    * 
    */
    private void init() {
   		
   		setModal(true);
        setHeight("80.0%");
        setWidth("80.0%");
        setResizable(true);
        addStyleName("color1");
        
   		
   		addAttachListener(new AttachListener() {
			
			@Override
			public void attach(AttachEvent event) {
				initContent();
				
			}
		});
    }
    
    
    interface saveListener extends Listener {
    }

    public class saveEvent extends Event {
        private static final long serialVersionUID = 8861261985522866553L;

        public saveEvent(final Component source) {
            super(source);
        }
    }
    @Override
    public void setCaption(String caption) {
    	super.setCaption(I18nUtils.getMessage(caption, caption));
    }
    
    @SuppressWarnings("serial")
    protected void build() {
    	
    	vl.removeAllComponents();
    	tabSheet.removeAllComponents();
    	
        vl.setSizeFull();
        vl.addStyleName("v-windows-contents");
        
        vl.removeAllComponents();
        
        setContent(vl);

        tabSheet.setSizeFull();
        tabSheet.addStyleName("framed");
        tabSheet.addStyleName("overflow");
        

        vl.addComponent(tabSheet);
        vl.setExpandRatio(tabSheet, 1);

        if (isNewItem()){
        	createButton = new Button(I18nUtils.getMessage("smc.button.add", "Create"));
        	createButton.addStyleName("primary");
        	setCaption(I18nUtils.getMessage("panel.new",""));
        }else{
        	saveButton = new Button(I18nUtils.getMessage("smc.button.save", "Save"));
        	saveButton.addStyleName("primary");
        	cancelButton = new Button(I18nUtils.getMessage("smc.button.cancel", "Cancel"));
        	setCaption(I18nUtils.getMessage("panel.upd",""));
        }
      
     
        
        final HorizontalLayout bottomToolBar = new HorizontalLayout();
        bottomToolBar.setWidth("100%");
        bottomToolBar.addStyleName("v-window-bottom-toolbar");
        bottomToolBar.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        
        Label toolBarText = new Label();
        bottomToolBar.addComponent(toolBarText);
        if(!isNewItem()) {
        	bottomToolBar.addComponent(saveButton);
        	bottomToolBar.addComponent(cancelButton);
        	 bottomToolBar.setExpandRatio(saveButton, 0.20F);
             bottomToolBar.setExpandRatio(cancelButton, 0.20F);
        }else{
        	bottomToolBar.addComponent(createButton);
        
        	bottomToolBar.setExpandRatio(createButton, 0.40F);
        }
        bottomToolBar.setExpandRatio(toolBarText, 0.60F);
        
        vl.addComponent(bottomToolBar);
       
        
  
       
        if (!isNewItem()){
	        cancelButton.addClickListener(new Button.ClickListener() {
	
	            @Override
	            public void buttonClick(final Button.ClickEvent event) {
	            	delete();
	            }
	        });
	
	        saveButton.addClickListener(new Button.ClickListener() {
	
	            @Override
	            public void buttonClick(final Button.ClickEvent event) {
	            	save();
	            }
	        });
        }else{
        	createButton.addClickListener(new Button.ClickListener() {
        		
	            @Override
	            public void buttonClick(final Button.ClickEvent event) {
	            	create();
	            }
	        });
        }
        	
        addActionHandler(this);
    }

    @Override
    public void close() {
        if (isModified()) {
        	MessageBox message = MessageBox.createQuestion();
        	message.createQuestion().withCaption("Warning !").
        	withMessage("sei sicuro di abbandonare tutte le modifiche ?").
        	withYesButton(()->{discardAllComponent(); super.close();}).withNoButton().open();
        } 
        		

    }

    @SuppressWarnings("rawtypes")
	private boolean isModified() {
        for (final EntityComponent component : componentList) {
            if (component instanceof EntityFormComponent) {
                if (((EntityFormComponent) component).isModifield()) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    private void discardAllComponent() {
        for (final EntityComponent component : componentList) {
            if (component instanceof EntityFormComponent) {
                if (component.isEnabled())
                    ((EntityFormComponent) component).discard();
            }

        }
    }

    @SuppressWarnings("rawtypes")
    private boolean saveAllComponent() {
        fireEvent(new saveEvent(this));
        try {
            for (final EntityComponent component : componentList) {
                if (component instanceof EntityFormComponent) {
                    if (component.isEnabled() &&  ((EntityFormComponent) component).isModifield()) 
                        ((EntityComponent) component).save();
                }
            }
            if (!item.isPersistent())
            	item = item.getContainer().getItem(item.getItemProperty(getItemPrimaryIdproperty()).getValue());
            return true;
        } catch (final CommitException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void save(){
        if (validateAllFormComponent()) {
           if( saveAllComponent());
            	close();	
        }
    }
    public void create(){

        if (isModified() && validateAllFormComponent()) {
           if (saveAllComponent()){
           	if (!singletab){
           		setNewItem(false);
           		initContent();
           	}else
           		close();
           	
           }
        }
    }
    
    public void delete(){
    	MessageBox.createInfo().withCaption("Warning !").withMessage("se sicuro di abbandonare tutte le modifiche ?")
    	.withYesButton(()->discardAllComponent()).open();
     }
    	
    
    public void add(){
    	
    }

    @SuppressWarnings("rawtypes")
    private boolean validateAllFormComponent() {
        for (final EntityComponent component : componentList) {
            if (component instanceof EntityFormComponent) {
                if (((EntityFormComponent) component).isModifield() && !((EntityFormComponent) component).isValid()) {
                	MessageBox.createInfo().withCaption("Error !").withMessage("sono presenti errori di validazione !")
                	.withOkButton(()-> tabSheet.setSelectedTab(component)).open();
                    return false;
                }
            }
        }
        return true;
    }

    public void addTab(final EntityComponent<?> component, final String caption) {
        component.init();
        component.setCaption(caption);
        componentList.add(component);
    }

    
    
   
    @Override
    public ShortcutAction[] getActions(final Object target, final Object sender) {
        return actions;
    }

    @Override
    public void handleAction(com.vaadin.event.Action action, Object sender, Object target) {
    	if (action == esc) {
            close();
        }
    }
 

    public EntityItem getEntityItem(){
    	return this.item;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setEntityItem(final EntityItem entityItem) {
    	this.item = entityItem;
    	setNewItem(!entityItem.isPersistent());
    }

    public void initContent() {
    	
    	build();
    	
    	for (final EntityComponent c : componentList) {
            if (c instanceof EntityComponentChild) {
              //  if (!newItem)
                    ((EntityComponentChild) c).setParentEntity(this.item);
            } else if (c instanceof EntityFormComponent)
                ((EntityFormComponent) c).setEntityItem(this.item);

          
            tabSheet.addTab(c, c.getCaption());
        }
   	
    	if (!isNewItem()){
	        if (readOnly) {
	            saveButton.setEnabled(false);
	        } else
	            saveButton.setEnabled(true);
    	}
    	
        int position = 0;
        for (@SuppressWarnings("rawtypes")
        final EntityComponent c : componentList) {
            if (c instanceof MasterTableComponent) {
                if (!newItem)
                    c.setEnabled(true);
                else
                    c.setEnabled(false);
            } 
            if (newItem) {
                if (position > 0)
                    tabSheet.getTab(c).setVisible(false);
            } else
                tabSheet.getTab(c).setVisible(true);

            position++;
        }
        singletab = (position==1);
        
        tabSheet.setSelectedTab(0);
    }
    
    private String getItemPrimaryIdproperty(){
    	return MetadataFactory.getInstance().getEntityClassMetadata(item.getEntity().getClass()).getIdentifierProperty().getName();
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isNewItem() {
        return newItem;
    }

    public void setNewItem(final boolean newItem) {
        this.newItem = newItem;
    }

    public int getTabComponentCount() {
        return tabSheet.getComponentCount() ;
    }

	public TabSheet getTabSheet() {
		return tabSheet;
	}

}
