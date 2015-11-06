package com.serpics.vaadin.ui;


import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.orderedlayout.AbstractOrderedLayoutState.ChildComponentData;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LoginView extends VerticalLayout {

    public LoginView() {            
    }

    public Component getLoginPanel(){
    	 setSizeFull();
         Component loginForm = buildLoginForm();
         addComponent(loginForm);
         setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);   
         return loginForm;
    }
    
    public Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(new CheckBox("Remember me", true));
        return loginPanel;
    }

    public Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);       

        final TextField username = new TextField("Username");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField("Password");
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        final Button signin = new Button("Sign In");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(KeyCode.ENTER);
        signin.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                     //TODO
            }
        });
        return fields;
    }

    public Component buildLabels() {
        CssLayout labels = new CssLayout(); 
        Label welcome = new Label("Admin Commerce Platform");        
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);      
        return labels;
    }
    
    @Override
    public void setComponentAlignment(Component childComponent,
            Alignment alignment) {
        ChildComponentData childData = getState().childData.get(childComponent);
        if (childData != null) {          
            childData.alignmentBitmask = alignment.getBitMask();
        } else {
            throw new IllegalArgumentException(
                    "Component must be added to layout before using setComponentAlignment()");
        }
    }    
    
    public void showInitNotification(){
    	Notification notification = new Notification("Commerce PlaTform Notification");
        notification.setDescription("<span>This application is not real, it only demonstrates an application built with the <a href=\"https://vaadin.com\">Vaadin framework</a>.</span> <span>No username or password is required, just click the <b>Sign In</b> button to continue.</span>");
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(20000);
        notification.show(Page.getCurrent());
    }
    
    public void componentAdded(Component component) {
        ChildComponentData ccd = new ChildComponentData();
        ccd.alignmentBitmask = getDefaultComponentAlignment().getBitMask();
        getState().childData.put(component, ccd);
    }

    @Override
    public void removeComponent(Component c) {        
        super.removeComponent(c);      
    }

}
