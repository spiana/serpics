/**
 * Copyright 2015-2016 StepFour Srl
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
package com.serpics.vaadin.ui.component;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.data.utils.I18nUtils;
import com.serpics.vaadin.ui.FilterComponentUtils;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TextField;

/**
 * @author spiana
 * @param <T>
 *
 */
public class FilterComponent<T> extends CustomComponent {

	private static transient Logger LOG = LoggerFactory.getLogger(FilterComponent.class);

	HorizontalLayout main;
	private String propertyId;	
	private MenuBar mb;
	private MenuItem mi;
	
	// private Map<String , Filter> appliedFilter = new HashMap<String, Container.Filter>();

	protected transient JPAContainer<T> container;

	private FilterComponentUtils filterComponentUtils;

	public FilterComponent() {

		super();

	}

	public void buildContent() {

		LOG.info("entry buildContent");
		this.main = new HorizontalLayout();
		this.main.setId("hl-"+this.propertyId);
		this.mb = new MenuBar();
		mb.setId("mb-"+this.propertyId);
		this.mi = mb.addItem(printLabelLang(this.propertyId), null);

		LOG.info("HL"  + " hl-"+this.propertyId);
		LOG.info("mb"  + " mb-"+this.propertyId);
		if (String.class.isAssignableFrom(this.container.getType(this.propertyId)))
			menuStrigItem();
		else if (MultilingualString.class.isAssignableFrom(this.container.getType(propertyId)))
			menuStrigItem();
		else if (Date.class.isAssignableFrom(this.container.getType(propertyId)))
			menuDateItem();
		else if (Double.class.isAssignableFrom(this.container.getType(propertyId)))
			menuNumericItem();
		else if (Boolean.class.isAssignableFrom(this.container.getType(propertyId)))
			menuBooleanItem();
		this.main.addComponentAsFirst(this.mb);
		this.main.setSpacing(true);
		setCompositionRoot(this.main);

	}

	protected void menuStrigItem() {
		MenuItem item = this.mi.addItem(printFilterLabelLang("equals"), stringChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("startwith"), stringChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("like"), stringChecked);
		item.setCheckable(true);

		TextField searchString = new TextField();
		searchString.setCaption(printLabelLang(getName(this.getId())));
		searchString.setTextChangeTimeout(200);
		searchString.setTextChangeEventMode(TextChangeEventMode.LAZY);
		searchString.setVisible(false);
		searchString.setValue("");
		searchString.setImmediate(true);
		searchString.setId("absf-"+ this.propertyId);
		filterComponentUtils.get().filterAllContainerJPA(container, this.propertyId, this.mi,
				(AbstractTextField) searchString);
		
		this.main.addComponent(searchString);
	}

	private Command stringChecked = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			AbstractTextField cs = (AbstractTextField) main.getComponent(1);
			managerMenu(selectedItem, (AbstractField) cs, null);
		}
	};

	private void menuDateItem() {
		// TODO Auto-generated method stub
		MenuItem item = this.mi.addItem(printFilterLabelLang("before"), dataChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("after"), dataChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("between"), dataBetweenChecked);
		item.setCheckable(true);

		DateField dataField = new DateField();
		dataField.setCaption(printLabelLang(getName(this.getId())));
		dataField.setValue(new Date());
		dataField.setVisible(false);
		
		dataField.setId("absf-"+ this.propertyId);
		this.main.addComponent(dataField);

		DateField dataEnd = new DateField();
		dataEnd.setValue(new Date());
		dataEnd.setCaption(printLabelLang(getName(this.getId())));
		dataEnd.setVisible(false);
		dataEnd.setId("absfe-"+ this.propertyId);
		
		filterComponentUtils.get().filterAllContainerJPA(container, this.propertyId, this.mi, (AbstractField) dataField, (AbstractField) dataEnd);
		filterComponentUtils.get().filterAllContainerJPARevert(container, this.propertyId, this.mi,  (AbstractField) dataEnd,(AbstractField) dataField);

		this.main.addComponent(dataEnd);
	}

	private Command dataChecked = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			DateField cs = (DateField) main.getComponent(1);
			DateField ce = (DateField) main.getComponent(2);
			if(ce.isVisible()) {
				ce.setValue(new Date());
				ce.setVisible(false);
			}
 			managerMenu(selectedItem, (AbstractField) cs, null);
		}
	};

	private Command dataBetweenChecked = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			// TextField tf = (TextField)main.getComponent(1);
			DateField cs = (DateField) main.getComponent(1);
			DateField ce = (DateField) main.getComponent(2);
			managerMenu(selectedItem, (AbstractField) cs, (AbstractField) ce);
		}
	};

	private void menuNumericItem() {
		MenuItem item = this.mi.addItem(printFilterLabelLang("lessequal"), numberChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("greaterequal"), numberChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("between"), numberBetweenChecked);
		item.setCheckable(true);

		TextField textField = new TextField();
		textField.setCaption(this.propertyId);
		textField.setVisible(false);
		textField.setValue("0");
		textField.setConverter(Integer.class);
		textField.setId("absf-"+ this.propertyId);
		this.main.addComponent(textField);

		TextField textFieldEnd = new TextField();
		textFieldEnd.setCaption(this.propertyId);
		textFieldEnd.setVisible(false);
		textFieldEnd.setValue("0");
		textFieldEnd.setConverter(Integer.class);
		textFieldEnd.setId("absfe-"+ this.propertyId);
		this.main.addComponent(textFieldEnd);
		
		filterComponentUtils.get().filterAllContainerJPA(container, this.propertyId, this.mi, (AbstractField) textField, (AbstractField) textFieldEnd);

	}

	private Command numberChecked = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			TextField cs = (TextField) main.getComponent(1);
			TextField ce = (TextField) main.getComponent(2);
			managerMenu(selectedItem, (AbstractField) cs, null);
		}
	};

	private Command numberBetweenChecked = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			TextField cs = (TextField) main.getComponent(1);
			TextField ce = (TextField) main.getComponent(2);
			managerMenu(selectedItem, (AbstractField) cs, (AbstractField) ce);
		}
	};

	
	private void menuBooleanItem() {
		MenuItem item = this.mi.addItem(printFilterLabelLang("true"), boolChecked);
		item.setCheckable(true);
		item = this.mi.addItem(printFilterLabelLang("false"), boolChecked);
		item.setCheckable(true);
	}
	
	private Command boolChecked = new Command() {
		public void menuSelected(MenuItem selectedItem) {
			boolean isChange = oneCheck(selectedItem);
			
			List<MenuItem> parent = selectedItem.getParent().getChildren();
			Boolean value = false;
			
			if(parent.get(0).isChecked() || parent.get(1).isChecked()) {
				if(parent.get(0).isChecked())  {
					value = true;
					parent.get(1).setChecked(false);
					
				} else {
					value = false;
					parent.get(0).setChecked(false);
				}
				mb.getItems().get(0).setText(printLabelLang(getName(mb.getId())) + ": " + selectedItem.getText());
				CheckBox cb = new CheckBox();
				cb.setValue(value);
				cb.setVisible(false);
				
				filterComponentUtils.get().reloadFilterJpa(container, getName(mb.getId()), selectedItem, (AbstractField) cb, null);
			}else  {
				mb.getItems().get(0).setText(printLabelLang(getName(mb.getId())));
				filterComponentUtils.get().removeFilterJpa(container,getName(mb.getId()));
			}
			
		}
	};

	private void managerMenu(MenuItem selectedItem, AbstractField<T> cs, AbstractField<T> ce) {
		LOG.info("Menager menu - entry");
		if (selectedItem.isChecked()) {
			boolean isChange = oneCheck(selectedItem);
			if ((cs != null) && !cs.isVisible()) {
				cs.setCaption(null);
				cs.setVisible(true);
			}
			if ((ce != null) && !ce.isVisible()){
				ce.setCaption(null);
				ce.setVisible(true);				
			}
			
			if(isChange) filterComponentUtils.get().reloadFilterJpa(container, getName(this.mb.getId()), selectedItem, cs, ce); //AGGIORNA FILTRO SE CAMBIO TIPO DI RICERCA
			String fieldTextCaption = cs.getCaption();
			//LOG.info("FIELD TEXT " + fieldTextCaption);
			
			this.mb.getItems().get(0).setText(printLabelLang(getName(this.mb.getId())) + ": " + selectedItem.getText());
			//LOG.info("ID PARENT" + selectedItem.getParent().getId() + selectedItem.getParent().getText());
			//LOG.info("selectedItem" +selectedItem.getId() + "-" + selectedItem.getText());
			
		} else {
			this.mb.getItems().get(0).setText(printLabelLang(getName(this.mb.getId())));
			cs.setImmediate(true);
			if ((cs != null) && cs.isVisible())
				cs.setVisible(false);
			if ((ce != null) && ce.isVisible())
				ce.setVisible(false);
			filterComponentUtils.get().removeFilterJpa(container,getName(this.mb.getId()));

		}
	}

	/* @descritpion: solo un check per menu */
	private boolean oneCheck(MenuItem selectedItem) {
		List<MenuItem> parent = selectedItem.getParent().getChildren();
		boolean isChange = false;
		for (MenuItem menuItem : parent) {
			if (!(menuItem.equals(selectedItem)) && menuItem.isChecked()) {
				menuItem.setChecked(false);
				isChange = true;
				break;
			}
		}
		return isChange;
	}

	public void setContainer(JPAContainer<T> container) {
		this.container = container;
	}

	public void removeContent(String id, HorizontalLayout filterPanel) {
		//Component root = getCompositionRoot();
		LOG.info("removeContent - id main" + this.main.getId() + " id menubar " + this.mb.getId() + " id attule " + id);
		filterComponentUtils.get().removeFilterJpa(container,id);
		Component c = filterComponentUtils.get().findComponentById(filterPanel, "fc-" + id);
		filterPanel.removeComponent(c);
	}
	
	public void setCaption(String caption) {
		this.propertyId = caption;
	}
	
	private String getName(String name) {
		int idx = name.indexOf("-");
		LOG.info("get correct name start " + name + "[" + idx +  "] esc --> " + name.substring(idx+1));
		return name.substring(idx+1);
	}
	
	private String printLabelLang(String name){
		return I18nUtils.getMessage(container.getEntityClass().getSimpleName().toLowerCase() + "." + name,name);
	}
	private String printFilterLabelLang(String name){
		return I18nUtils.getMessage("filter." + name,name);
	}
}