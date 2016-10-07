package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.EntityComponent.EntityComponentChild;
import com.serpics.vaadin.ui.EntityComponent.EntityFormComponent;
import com.serpics.vaadin.ui.EntityComponent.MasterTableComponent;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

public class EntityFormWindow<T> extends Window implements Handler {
    private static final long serialVersionUID = 2590755708760150150L;

    private final TabSheet tabSheet = new TabSheet();
    static final Action esc = new ShortcutAction("Close window", ShortcutAction.KeyCode.ESCAPE, null);
    static final Action[] actions = new Action[] { esc };

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
   		setImmediate(true);
        
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
        	createButton = new Button("Create");
        	createButton.addStyleName("primary");
        }else{
        	saveButton = new Button("Save");
        	saveButton.addStyleName("primary");
        	cancelButton = new Button("Cancel");
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
	            public void buttonClick(final ClickEvent event) {
	            	delete();
	            }
	        });
	
	        saveButton.addClickListener(new Button.ClickListener() {
	
	            @Override
	            public void buttonClick(final ClickEvent event) {
	            	save();
	            }
	        });
        }else{
        	createButton.addClickListener(new Button.ClickListener() {
        		
	            @Override
	            public void buttonClick(final ClickEvent event) {
	            	create();
	            }
	        });
        }
        	
        addActionHandler(this);
    }

    @Override
    public void close() {
        if (isModified()) {
            MessageBox.showPlain(Icon.QUESTION, "Attenzione !", "se sicuro di abbandonare tutte le modifiche ?",
                    new MessageBoxListener() {
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    if (buttonId.compareTo(ButtonId.NO) == 0)
                        return;
                }

            }, ButtonId.NO, ButtonId.YES);
        }
        discardAllComponent();
        super.close();
        

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
                if (component.isEnabled() && !component.isReadOnly())
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
                    if (component.isEnabled() && !component.isReadOnly() && ((EntityFormComponent) component).isModifield()) 
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
    	 MessageBox.showPlain(Icon.QUESTION, "Attenzione !", "se sicuro di abbandonare tutte le modifiche ?",
                 new MessageBoxListener() {
             @Override
             public void buttonClicked(final ButtonId buttonId) {
                 if (buttonId.compareTo(ButtonId.YES) == 0) {
                     discardAllComponent();
                     close();
                 }
             }

         }, ButtonId.NO, ButtonId.YES);

     }
    	
    
    public void add(){
    	
    }

    @SuppressWarnings("rawtypes")
    private boolean validateAllFormComponent() {
        for (final EntityComponent component : componentList) {
            if (component instanceof EntityFormComponent) {
                if (((EntityFormComponent) component).isModifield() && !((EntityFormComponent) component).isValid()) {
                    MessageBox.showPlain(Icon.ERROR, "Error", "sono presenti errori di validazione !", ButtonId.OK);
                    tabSheet.setSelectedTab(component);
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
    public Action[] getActions(final Object target, final Object sender) {
        return actions;
    }

    @Override
    public void handleAction(final Action action, final Object sender, final Object target) {
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
            } else {
                c.setReadOnly(readOnly);
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
