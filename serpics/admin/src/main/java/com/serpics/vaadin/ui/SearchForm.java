package com.serpics.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import com.serpics.vaadin.ui.component.CustomFieldFactory;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.metadata.MetadataFactory;
import com.vaadin.addon.jpacontainer.metadata.PropertyKind;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;

public abstract class SearchForm<T> extends FormLayout{

	private static final long serialVersionUID = 87868509261251877L;
	private BeanFieldGroup<T> formFiledBinding;
	private transient PropertyList<T> propertyList;	
	protected transient Class<T> entityClass;
	protected transient EntityItem<T> item ;
	
	private transient String[] searchProperties;
	
	public SearchForm(Class<T> entityClass , EntityItem<T> item) {
		super();
		this.entityClass = entityClass;
		this.item = item;
		this.propertyList = new PropertyList<T>(MetadataFactory.getInstance()
				.getEntityClassMetadata(entityClass));
		searchProperties = PropertiesUtils.get().getSearchProperty(entityClass.getSimpleName());
		
		if (searchProperties == null){
			this.searchProperties = buildDisplayProperties(entityClass);
		}
		buildContent();
	}
	
	private String[] buildDisplayProperties(Class<?> referencedType){
		List<String> properties = new ArrayList<String>();
		for (Object pid : propertyList.getAllAvailablePropertyNames()) {
			 if (propertyList.getClassMetadata().getProperty(pid.toString())
					.getAnnotation(Id.class) == null)
				if (propertyList.getClassMetadata().getProperty(pid.toString())
						.getAnnotation(EmbeddedId.class) == null)
					if (propertyList.getPropertyKind(pid.toString()).equals(
							PropertyKind.SIMPLE))
								properties.add(pid.toString());
		}
		
		return properties.toArray(new String[]{});
		
		
	}
	
	private T createEmptyInstance(){
		try {
			return (T) entityClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void buildContent(){
		formFiledBinding = new BeanFieldGroup<T>(entityClass);
		formFiledBinding.setItemDataSource(createEmptyInstance());
		for (String pid : searchProperties) {
			if(pid.contains("."))
				propertyList.addNestedProperty(pid);
			
			Field<?> field = CustomFieldFactory.get().createField(item, pid, this);
			formFiledBinding.bind(field, pid);
			addComponent(field);
		}
		Button search= new Button("search");
		search.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				search();
			}
		});
		
		addComponent(search);
		
	}
	
	public void search() {
		try {
			formFiledBinding.commit();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
