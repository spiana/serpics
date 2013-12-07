package com.serpics.vaadin.ui.memeship;

import com.serpics.membership.persistence.User;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.ui.EntityForm;
import com.vaadin.ui.Field;

@VaadinComponent("userEditorComponent")
public class UserEditorComponent extends EntityForm<User>{
	private static final long serialVersionUID = 1L;
	
	public UserEditorComponent() {
		
		super(User.class);
		
		String[] displayProperties ={"firstname" , "lastname" , "phone" , "email" , "created"};
		this.displayProperties= displayProperties;
		
		
	}
	
	@Override
	protected Field<?> createField(String pid) {
		Field<?> f = super.createField(pid);
		
		if (pid.equals("created"))
			f.setReadOnly(true);
		
		return  f;
	}
}
