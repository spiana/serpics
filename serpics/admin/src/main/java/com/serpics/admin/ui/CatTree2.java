package com.serpics.admin.ui;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.admin.CatPopulator;
import com.serpics.admin.EntityFormEditor;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.services.CatalogService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
public class CatTree2 extends CustomComponent implements InitializingBean {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalSplitPanel horizontalSplitPanel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private EntityFormEditor<Category> form;
	@AutoGenerated
	private Tree catTree;
//	BeanFieldGroup fg = new BeanFieldGroup<Category>(Category.class);
	
	private BeanContainer<String, Category> catBeanContainer = new BeanContainer<String, Category>(Category.class);
	
	@Resource
	CatalogService catalogService;
	
	@Resource
	CatPopulator catP;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CatTree2() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		catBeanContainer.setBeanIdProperty("code");		
//		form.addComponent(fg.buildAndBind("code"));
//		form.setWidth("250px");
//		catP.dostuff();
		for (Category ca : catalogService.findRootCategory()){
			System.out.println(ca.getCode());
			recurseTree(ca, null);
		}
	
		
		   
	}
	
	private void recurseTree(Category ca, Category parent){

		
		BeanItem i = catBeanContainer.addBean(ca);

//		Item parent = catTree.getItem(catTree.addItem());
//		for (Object propID : i.getItemPropertyIds()){
//			catTree.addContainerProperty(propID, i.getItemProperty(propID).getClass(), null);
//			parent.addItemProperty(propID, i.getItemProperty(propID));
//		}
		
		catTree.addItem(ca.getCode());
		
		if (parent != null)
			catTree.setParent(ca.getCode(), parent.getCode());
		
		List<Category> childCategories = catalogService.getChildCategories(ca);
		if (!childCategories.isEmpty()){
			catTree.setChildrenAllowed(ca.getCode(), true);
			for (Category c : childCategories){
				recurseTree(c, ca);
			}
		}
		
		catTree.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				BeanItem i = catBeanContainer.getItem(event.getItemId());
				form.setEntityItem(i);

			}
		});
		
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalSplitPanel_1
		horizontalSplitPanel_1 = buildHorizontalSplitPanel_1();
		mainLayout.addComponent(horizontalSplitPanel_1);
		mainLayout.setExpandRatio(horizontalSplitPanel_1, 1.0f);
		
		return mainLayout;
	}


	@AutoGenerated
	private HorizontalSplitPanel buildHorizontalSplitPanel_1() {
		// common part: create layout
		horizontalSplitPanel_1 = new HorizontalSplitPanel();
		horizontalSplitPanel_1.setImmediate(false);
		horizontalSplitPanel_1.setWidth("100.0%");
		horizontalSplitPanel_1.setHeight("100.0%");
		
		// catTree
		catTree = new Tree();
		catTree.setImmediate(false);
		catTree.setWidth("-1px");
		catTree.setHeight("-1px");
		horizontalSplitPanel_1.addComponent(catTree);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		horizontalSplitPanel_1.addComponent(verticalLayout_2);
		
		return horizontalSplitPanel_1;
	}


	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("-1px");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(false);
		
		// form
		form = new EntityFormEditor<Category>(Category.class);
		form.setImmediate(false);
		form.setWidth("-1px");
		form.setHeight("-1px");
		form.setMargin(false);
		form.setSpacing(true);
		verticalLayout_2.addComponent(form);
		verticalLayout_2.setMargin(true);

		
		return verticalLayout_2;
	}




}
