package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serpics.base.data.model.MultilingualString;
import com.serpics.vaadin.ui.component.MultilingualTextField;
import com.serpics.vaadin.ui.filter.MultilingualLikeFilter;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.filter.Filters;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class FilterComponentListener implements Container.Filter {

	private static final long serialVersionUID = -2736583181645447496L;
	private static transient Logger LOG = LoggerFactory.getLogger(FilterComponentListener.class);

	 protected String propertyId;
	 
	@Transient
	private transient String[] searchProperties;

	private static FilterComponentListener instance;

	
	public FilterComponentListener() {
		super();
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

	public <T> void filterAllContainerJPA(final JPAContainer<T> container, final String entry, final MenuItem item, final AbstractTextField absField) {
		
		absField.addTextChangeListener(new TextChangeListener() {
			
			@Override
			public void textChange(TextChangeEvent event) {
				LOG.info("text change" + event.getText());
				removeFilterJpa(container, entry);
				AbstractTextField c = (AbstractTextField)event.getComponent();
				c.setValue(event.getText());
				if (event.getText().equals("")) showNotificationMessage("Remove all filter from container!!");
				else addFilter(container, entry, item, c, null); //CREA TIPOLOGIA FILTRIO
			}
		});
	}	
	
	public <T> void filterAllContainerJPARevert(final JPAContainer<T> container, final String entry, final MenuItem item, final AbstractField absFieldEnd, final AbstractField absField)  {
		
		absFieldEnd.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				LOG.info("text change" + event.getProperty().getValue() + "  " +  event.getClass().toString());
				removeFilterJpa(container, entry);
				if (event.getProperty().getValue().equals("")) showNotificationMessage("Remove all filter from container!!");
				else addFilter(container, entry, item, absField, absFieldEnd); //CREA TIPOLOGIA FILTRIO girando correttasmente i valori
			}
		});
	}	
	public <T> void filterAllContainerJPA(final JPAContainer<T> container, final String entry, final MenuItem item, final AbstractField absField, final AbstractField absFieldEnd) {
		
		absField.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				LOG.info("text change" + event.getProperty().getValue() + "  " +  event.getClass().toString());
				removeFilterJpa(container, entry);
				if (event.getProperty().getValue().equals("")) showNotificationMessage("Remove all filter from container!!");
				else addFilter(container, entry, item, absField, absFieldEnd); //CREA TIPOLOGIA FILTRIO
				//else addFilteraddFilterJpa(container, entry, item, absField, absFieldEnd); //CREA TIPOLOGIA FILTRIO
			}
		});
	}	
	
	private  <T> void  addFilter(final JPAContainer<T> container, final String entry, MenuItem item,  final AbstractField dataField, final AbstractField dataFieldEnd){

		FilteringMode filteringMode = null;
		Filter filter = null;
		List<Filter> filters = new ArrayList<Filter>();
		//Collection<Filter> filters = container.getContainerFilters();
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
			filters.add(filter);
		}
		container.addContainerFilter(Filters.and(filters));
	}
	
	 protected Filter buildMultilinguaStringFilter(String entry, String filterString, FilteringMode filteringMode) {
		 	Locale locale = UI.getCurrent().getSession().getLocale();
	        Filter filter = null;
	        if (null != filterString && !"".equals(filterString)) {
	            switch (filteringMode) {
	            case EQUALS:
	            	filterString = "'" + filterString + "'";
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
	            	filterString = "'" + filterString + "'";
	            	filter = new SimpleStringFilter(entry,filterString, false, true);
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
	
	public <T> void reloadFilterJpa(final JPAContainer<T> container,final String entry, MenuItem selectedItem, final AbstractField absField, final AbstractField absFieldEnd){
		LOG.info("reloadFilterJpa " );
	 	this.removeFilterJpa(container, entry);
		if((absField != null) || (absFieldEnd != null)) this.addFilter(container, entry, selectedItem.getParent(), absField, absFieldEnd);
		
	}
	
	public <T> void removeFilterJpa(final JPAContainer<T> container,String entry){
		 	LOG.info("removeFilterJpa " );
		 	Filter filter = getFilterJpa(container, entry);
			if(filter != null) container.removeContainerFilter(filter);
	 }
	public <T> void removeAllFilterJpa(final JPAContainer<T> container){
	 	LOG.info("remove allFilterJpa " );
	 	container.removeAllContainerFilters();
	 	
 }
	 private <T> Filter  getFilterJpa(final JPAContainer<T> container, final String entry){
		 LOG.info("getFilterJpa emtry " );
		 List<Filter> lf = container.getAppliedFilters();
		 Filter currentFilter = null;
		 Collection<Filter> cf = container.getContainerFilters();
		 if(!cf.isEmpty()) {
			
			 Filter children = cf.iterator().next();
			 boolean findFilter = false;
			 for (Filter f : lf) {
				 if(And.class.isAssignableFrom(f.getClass())) LOG.info("v");
				 And a = (And) f;
				 Collection<Filter> sf = a.getFilters();
				 for (Filter filter : sf) {
					 And sa = (And) filter;
					 if(SimpleStringFilter.class.isAssignableFrom(sa.getFilters().getClass())) LOG.info("finalement");
					 if(MultilingualLikeFilter.class.isAssignableFrom(sa.getFilters().getClass())) LOG.info("finalement multi");
					 
					 Filter thisF = (Filter)sa.getFilters().iterator().next();
					 if(SimpleStringFilter.class.isAssignableFrom(thisF.getClass())) {
						 final SimpleStringFilter ssf = (SimpleStringFilter) thisF;
						 LOG.info("SimpleStringFilter " + ssf.getFilterString() + ssf.getPropertyId());
						 if(ssf.getPropertyId().equals(entry)) findFilter = true;
					 }
					 if(MultilingualLikeFilter.class.isAssignableFrom(thisF.getClass())) {
						 final MultilingualLikeFilter ssf = (MultilingualLikeFilter) thisF;
						 LOG.info("MultilingualLikeFilter " + ssf.getSubqueryProperty());
						 if(ssf.getSubqueryProperty().equals(entry)) findFilter = true;
					 }
					 if(Compare.LessOrEqual.class.isAssignableFrom(thisF.getClass())){
						 final Compare.LessOrEqual ssf = (Compare.LessOrEqual) thisF;
						 LOG.info("Compare less " + ssf.getPropertyId());
						 if(ssf.getPropertyId().equals(entry)) findFilter = true;
					 } else if(Compare.GreaterOrEqual.class.isAssignableFrom(thisF.getClass())){
						 final Compare.GreaterOrEqual ssf = (Compare.GreaterOrEqual) thisF;
						 LOG.info("Compare GreaterOrEqual " + ssf.getPropertyId());
						 if(ssf.getPropertyId().equals(entry)) findFilter = true;
					 } else  if(And.class.isAssignableFrom(thisF.getClass())){
						 final And ssf = (And) thisF;
						 Filter f1 = ssf.getFilters().iterator().next();
						try {
							final Compare.LessOrEqual cl = (Compare.LessOrEqual) f1;
							LOG.info("Compare less " + cl.getPropertyId());
							if(cl.getPropertyId().equals(entry)) findFilter = true;
						} catch(Exception _e) {
							LOG.info("NO SUB");
						}
					 } else if(Compare.Equal.class.isAssignableFrom(thisF.getClass())){
						 final Compare.Equal ssf = (Compare.Equal) thisF;
						 LOG.info("Compare less " + ssf.getPropertyId());
						 if(ssf.getPropertyId().equals(entry)) findFilter = true;
					 }
					 if(findFilter) {
						 currentFilter = a;
						 break;
					 }
				}
				if(findFilter) break;
				 
			 }
		 }
		
		 return currentFilter;
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
