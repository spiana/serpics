package com.serpics.admin.ui;

import javax.persistence.Transient;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.service.EntityService;
import com.serpics.core.session.CommerceSessionContext;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.LazyLoadingDelegate;
import com.vaadin.addon.jpacontainer.SerpicsPersistentContainer;
import com.vaadin.addon.jpacontainer.provider.SerpicsCachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.provider.SerpicsEntityProvider;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


@Component
@Scope("prototype")
public class CatTable extends CustomComponent implements InitializingBean {

	private static final long serialVersionUID = -3438115317897580511L;

	@Autowired
	private CategoryEntityServiceImpl catService;
	
	@Transient	
	private SerpicsCachingLocalEntityProvider<Category> provider;
	
//	@Autowired
//	private LazyLoadingDelegate serpicsHibernateLazyLoadingDelegate;
	
	public CatTable(){
		
		
		
	}
	
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		init();
	}

	public void init(){
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		setSizeFull();
		
		// top-level component properties
//		setWidth("90.0%");
//		setHeight("90.0%");
		
		SerpicsPersistentContainer<Category> cont = new SerpicsPersistentContainer<Category>(Category.class);
		
		
		provider = new SerpicsCachingLocalEntityProvider<Category>(Category.class);
		provider.setService((EntityService) getCatService());
		provider.setCacheEnabled(true);
		
//		provider.setLazyLoadingDelegate(serpicsHibernateLazyLoadingDelegate);
				
		cont.setEntityProvider(provider);
		
		Table t = new Table("cat", cont);
//		t.addGeneratedColumn("catalog", new Table.ColumnGenerator() {
//			
//			@Override
//			public Object generateCell(Table source, Object itemId, Object columnId) {
//				return new TextField("catalog");
//			}
//		});
		
		
		t.setSizeFull();
		mainLayout.addComponent(t);

		setCompositionRoot(mainLayout);
	}



	public CategoryEntityServiceImpl getCatService() {
		return catService;
	}



	public void setCatService(CategoryEntityServiceImpl catService) {
		this.catService = catService;
	}







	
}
