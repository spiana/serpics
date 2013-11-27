package com.serpics.vaadin.ui;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;

public interface EntityComponent extends Component{
	public void save() throws CommitException;
	public void discard();

}
