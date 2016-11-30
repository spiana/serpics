package com.serpics.vaadin.ui;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;

import org.hibernate.loader.collection.CollectionInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.FilterDefinitionFactoryBean;
import org.springframework.util.Assert;

import com.google.gwt.uibinder.rebind.Statements.Empty;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.ui.component.MultilingualTextField;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.filter.Filters;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FilterComponentListener implements Container.Filter {

	private static final long serialVersionUID = -2736583181645447496L;
	private static transient Logger LOG = LoggerFactory.getLogger(FilterComponentListener.class);

	 protected String propertyId;
	private Hashtable<String,Filter> filterList;
	@Transient
	private transient String[] searchProperties;

	private static FilterComponentListener instance;

	private static final String FILTER_LIST = "FILTER_LIST";
	
	public FilterComponentListener() {
		super();
		filterList = (Hashtable<String, Container.Filter>) VaadinSession.getCurrent().getAttribute("FILTER_LIST");
		if(filterList == null) {
			filterList = new Hashtable<String,Filter>();
			VaadinSession.getCurrent().setAttribute(FILTER_LIST, filterList);
		}
	
		
		
	}

	public static FilterComponentListener get() {
		if (instance == null)
			instance = new FilterComponentListener();
		return instance;
	}
	
	
	public enum FilteringMode {
	    OFF,EQUALS, STARTSWITH, CONTAINS,
		BEFORE, AFTER, BETWEEN;
	}

	public <T> void filterAllContainerJPA(final JPAContainer<T> container, final String entry, final MenuItem item, final AbstractTextField absField, final String id) {
		
		absField.addTextChangeListener(new TextChangeListener() {
			
			@Override
			public void textChange(TextChangeEvent event) {
				LOG.info("text change" + event.getText());
				//removeFilterJpa(container, entry, id);
				removeFilterJpa(container,  id);
				AbstractTextField c = (AbstractTextField)event.getComponent();
				c.setValue(event.getText());
				if (event.getText().equals("")) showNotificationMessage("Remove all filter from container!!");
				else addFilter(container, entry, item, c, null, id); //CREA TIPOLOGIA FILTRIO
			}
		});
	}	 
	
	public <T> void filterAllContainerJPARevert(final JPAContainer<T> container, final String entry, final MenuItem item, final AbstractField absFieldEnd, final AbstractField absField,final String id)  {
		
		absFieldEnd.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				LOG.info("text change" + event.getProperty().getValue() + "  " +  event.getClass().toString());
				//removeFilterJpa(container, entry, id);
				removeFilterJpa(container, id);
				if (event.getProperty().getValue().equals("")) showNotificationMessage("Remove all filter from container!!");
				else addFilter(container, entry, item, absField, absFieldEnd, id); //CREA TIPOLOGIA FILTRIO girando correttasmente i valori
			}
		});
	}	
	public <T> void filterAllContainerJPA(final JPAContainer<T> container, final String entry, final MenuItem item, final AbstractField absField, final AbstractField absFieldEnd, final String id) {
		
		absField.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				LOG.info("text change" + event.getProperty().getValue() + "  " +  event.getClass().toString());
				//removeFilterJpa(container, entry,id);
				removeFilterJpa(container, id);
				if (event.getProperty().getValue().equals("")) showNotificationMessage("Remove all filter from container!!");
				else addFilter(container, entry, item, absField, absFieldEnd, id); //CREA TIPOLOGIA FILTRIO
				//else addFilteraddFilterJpa(container, entry, item, absField, absFieldEnd); //CREA TIPOLOGIA FILTRIO
				
			}
		});
	}	
	
	
private  <T> void  addFilter(final JPAContainer<T> container, final String entry, MenuItem item,  final AbstractField dataField, final AbstractField dataFieldEnd, String id){

		FilteringMode filteringMode = null;
		Filter filter = null;
		//List<Filter> filters = new ArrayList<Filter>();
		//Collection<Filter> cf = container.getContainerFilters();
		//List<Filter> filters = container.getAppliedFilters();
		if (entry != null) {
			
			if (MultilingualString.class.isAssignableFrom(container.getType(entry))
					|| MultilingualTextField.class.isAssignableFrom(container.getType(entry))
					|| String.class.isAssignableFrom(container.getType(entry))
				) filteringMode = setFilterModeForString(item);
			else if(Date.class.isAssignableFrom(container.getType(entry))) 
				filteringMode = setFilterModeForDate(item);
			else if(Double.class.isAssignableFrom(container.getType(entry))) 
				filteringMode = setFilterModeForNumeric(item);
			else if(Boolean.class.isAssignableFrom(container.getType(entry))) 
				filteringMode = null;
			LOG.info("filter mode "  + filteringMode);
			if (MultilingualString.class.isAssignableFrom(container.getType(entry))) {
				filter = new And(buildMultilinguaStringFilter(entry, ((AbstractTextField)dataField).getValue(), filteringMode));
			}else if (MultilingualTextField.class.isAssignableFrom(container.getType(entry))) {
				LOG.warn("MultilingualTextField is not yet supported in container filters !");
			} else if(String.class.isAssignableFrom(container.getType(entry))) {
				filter = new And(buildSimpleStringFilter(entry, ((AbstractTextField)dataField).getValue(), filteringMode));
			} else if(Date.class.isAssignableFrom(container.getType(entry))) {
				filter = new And(buildDateFilter(entry, (DateField)dataField, (DateField)dataFieldEnd, filteringMode));
			} else if(Double.class.isAssignableFrom(container.getType(entry))) {
				if(dataFieldEnd != null)  filter = new And(buildNumericFilter(entry, (Integer)dataField.getConvertedValue(), (Integer)dataFieldEnd.getConvertedValue(), filteringMode));
				else filter = new And(buildNumericFilter(entry, (Integer)dataField.getConvertedValue(), null, filteringMode));
			} else if(Boolean.class.isAssignableFrom(container.getType(entry))) {	
				filter = new And(new Compare.Equal(entry, ((CheckBox) dataField).getValue()));
			} else LOG.info("NESSUN TIPO");
			filterList.put(id, filter);
			LOG.info("*** aggiungo a filterList " + item.getText() + " - " + filter.toString());
			//filters.add(filter);
			setHashListSession(filterList);
			
			
		}
		container.addContainerFilter(filter);
		
	}
	
	 protected Filter buildMultilinguaStringFilter(String entry, String filterString, FilteringMode filteringMode) {
		 	Locale locale = UI.getCurrent().getSession().getLocale();
	        Filter filter = null;
	        if (null != filterString && !"".equals(filterString)) {
	            switch (filteringMode) {
	            case EQUALS:
	            	filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString, true);
	                break;
	            case STARTSWITH:
	            	filterString = filterString + '%';
	            	filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString, false);
	                break;
	            case CONTAINS:
	            	filterString =  '%' + filterString + '%';
	                filter = new MultilingualLikeFilter(entry,locale.getLanguage(), filterString);
	                break;
	            }
	        }
	        return filter;
	    }

	 protected Filter buildSimpleStringFilter(String entry, String filterString, FilteringMode filteringMode) {
	        Filter filter = null;
	        if (null != filterString && !"".equals(filterString)) {
	            switch (filteringMode) {
	            case EQUALS:
	            	filter = new Compare.Equal(entry, filterString);
	                break;
	            case STARTSWITH:
	            	filterString = filterString + '%';
	                filter = new SimpleStringFilter(entry,filterString, true, true);
	                break;
	            case CONTAINS:
	            	filterString =  '%' + filterString + '%';
	                filter = new SimpleStringFilter(entry,filterString, true, false);
	                break;
	            }
	        }
	        return filter;
	    }
	 
	 protected Filter buildDateFilter(String entry, DateField dataField, DateField dataFieldEnd, FilteringMode filteringMode){
		 Filter filter = null;
		 if (null != dataField && !"".equals(dataField)) {
	            switch (filteringMode) {
	            case BEFORE:
	            	filter = new Compare.LessOrEqual(entry, dataField.getValue());
	                break;
	            case AFTER:
	            	filter = new Compare.GreaterOrEqual(entry, dataField.getValue());
	                break;
	            case BETWEEN:
	            	filter = new And(new Compare.LessOrEqual(entry, dataFieldEnd.getValue()), new Compare.GreaterOrEqual(entry, dataField.getValue()));
	                break;
	            }
	        }
		 return filter;
	 }
	 
	 protected Filter buildNumericFilter(String entry, Integer dataField, Integer dataFieldEnd, FilteringMode filteringMode){
		 Filter filter = null;
		 if (null != dataField && !"".equals(dataField)) {
	            switch (filteringMode) {
	            case BEFORE:
	            	filter = new Compare.LessOrEqual(entry, dataField);
	                break;
	            case AFTER:
	            	filter = new Compare.GreaterOrEqual(entry, dataField);
	                break;
	            case BETWEEN:
	            	filter = new And(new Compare.LessOrEqual(entry, dataFieldEnd), new Compare.GreaterOrEqual(entry, dataField));
	                break;
	            }
	        }
		 return filter;
	 }
	private FilteringMode setFilterModeForString(MenuItem item) {
		List<MenuItem> li = item.getChildren();
		FilteringMode filteringMode = FilteringMode.OFF;
		if(li.get(0).isChecked())  filteringMode = FilteringMode.EQUALS;//event.getText(); //UGUALE A
		if(li.get(1).isChecked()) filteringMode = FilteringMode.STARTSWITH;//event.getText() + "%"; //INIZIA CON
		if(li.get(2).isChecked()) filteringMode = FilteringMode.CONTAINS;//filterField= "%" + event.getText() + "%"; //CONTIENE
		return filteringMode;
	}
	
	private FilteringMode setFilterModeForDate(MenuItem item){
		List<MenuItem> li = item.getChildren();
		FilteringMode filteringMode = FilteringMode.OFF;
		if(li.get(0).isChecked())  filteringMode = FilteringMode.BEFORE;//event.getText(); //UGUALE A
		if(li.get(1).isChecked()) filteringMode = FilteringMode.AFTER;//event.getText() + "%"; //INIZIA CON
		if(li.get(2).isChecked()) filteringMode = FilteringMode.BETWEEN;//filterField= "%" + event.getText() + "%"; //CONTIENE
		return filteringMode;
	}
	
	private FilteringMode setFilterModeForNumeric(MenuItem item){
		List<MenuItem> li = item.getChildren();
		FilteringMode filteringMode = FilteringMode.OFF;
		if(li.get(0).isChecked())  filteringMode = FilteringMode.BEFORE;//event.getText(); //UGUALE A
		if(li.get(1).isChecked()) filteringMode = FilteringMode.AFTER;//event.getText() + "%"; //INIZIA CON
		if(li.get(2).isChecked()) filteringMode = FilteringMode.BETWEEN;//filterField= "%" + event.getText() + "%"; //CONTIENE
		return filteringMode;
	}
	
	public <T> void reloadFilterJpa(final JPAContainer<T> container,final String entry, MenuItem selectedItem, final AbstractField absField, final AbstractField absFieldEnd, final String id){
		LOG.info("reloadFilterJpa " );
	 	//this.removeFilterJpa(container, entry, id);
		this.removeFilterJpa(container, id);
		if((absField != null) || (absFieldEnd != null)) this.addFilter(container, entry, selectedItem.getParent(), absField, absFieldEnd, id);
		
	}
	
	//public <T> void removeFilterJpa(final JPAContainer<T> container,String entry, String id){
	public <T> void removeFilterJpa(final JPAContainer<T> container, String id){
		 	//LOG.info("removeFilterJpa "  + entry);
		 	filterList = (Hashtable<String, Container.Filter>) VaadinSession.getCurrent().getAttribute(FILTER_LIST);
		 	if(filterList == null) filterList = new Hashtable<String, Container.Filter>();
		 	boolean findfilter = filterList.containsKey(id);
		 	if(findfilter) {
		 		Filter filter = filterList.get(id);
		 		container.setApplyFiltersImmediately(true);
		 		container.removeContainerFilter(filter);
		 		filterList.remove(id);
		 		setHashListSession(filterList);
		 	}
		 	
		 	
	 }
	public <T> void removeAllFilterJpa(final JPAContainer<T> container){
	 	LOG.info("remove allFilterJpa " );
	 	container.removeAllContainerFilters();
	 	//devo rimuover sulla filter list tutti gli elementi che inizaono comcomponent name 
	 	filterList.clear();
	 	
	 	
 }
	
	private void setHashListSession(Hashtable<String, Container.Filter> ht) {
		VaadinSession.getCurrent().setAttribute(FILTER_LIST, ht);
	}
	 public static Component findComponentById(HasComponents root, String id) {
		    for (Component child : root) {
		        if (id.equals(child.getId())) {
		            return child; // found it!
		        } else if (child instanceof HasComponents) { // recursively go through all children that themselves have children
		            Component result = findComponentById((HasComponents) child, id);
		            if (result != null) {
		                return result;
		            }
		        }
		    }
		    return null; // none was found
		}
	private void showNotificationMessage(String message) {
		Notification notification = new Notification("Commerce PlaTform Notification");
		notification.setHtmlContentAllowed(true);
		notification.setDescription("<br /><span><p>" + message + "<br /></p></span>");
		notification.setPosition(Position.BOTTOM_RIGHT);
		notification.setDelayMsec(6000);
		notification.setStyleName("commerce-notification");
		notification.show(Page.getCurrent());
	}
	
	@Override
	public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
