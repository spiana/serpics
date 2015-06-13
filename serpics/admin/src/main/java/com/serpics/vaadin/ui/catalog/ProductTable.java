package com.serpics.vaadin.ui.catalog;

import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.jpacontainer.provider.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityForm;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.MultilingualStringConvert;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;


@VaadinComponent("productTable")
public class ProductTable extends MasterTable<Product> {
    private static final long serialVersionUID = 6586616418061870098L;

    private CategoryRepository categoryRepository;
    
    public ProductTable() {
        super(Product.class);
    }

    private EntityForm<Product> buildMainTab(){
    	return new EntityForm<Product>(Product.class) {
            @Override
            public void init() {
                super.init();
                setDisplayProperties(new String[]{"code" ,"description","prices"});
              //  setHideProperties(new String[] {  "field2", "productType", "ctentryType" , "ctentryAttributes" , "productffmts","medias" , "ctentryid" });
                setReadOnlyProperties(new String[] { "created", "updated" , "uuid"});

            }
        };
    }
    
    
    @Override
    public EntityFormWindow<Product> buildEntityWindow() {
    	 EntityFormWindow<Product> editorWindow = new EntityFormWindow<Product>();
    	 
    	 editorWindow.addTab(buildMainTab(), "main");
    	 editorWindow.addTab(buildPriceTab(), "prices");
    	 
 //   	 editorWindow.addTab(buildCategoriesTab(), "categories");
 //   	 editorWindow.addTab(buildPriceTab(), "prices");
    	 
    	return editorWindow;
    }
   
    private MasterDetailTable<CategoryProductRelation, AbstractProduct> buildCategoriesTab(){

    	return new MasterDetailTable<CategoryProductRelation, AbstractProduct>(
                CategoryProductRelation.class){
    		
        private static final long serialVersionUID = -2478612011226738573L;

        private transient JPAContainer<Category> categories;

        @Override
		public EntityFormWindow<CategoryProductRelation> buildEntityWindow() {
			
        	EntityFormWindow<CategoryProductRelation> editorWindow = new EntityFormWindow<CategoryProductRelation>();
        	
        	editorWindow.addTab(new EntityForm<CategoryProductRelation>(CategoryProductRelation.class) {

                 @Override
                 public void init() {
                     super.init();
                     setDisplayProperties(new String[] { "parentCategory", "sequence" });
                     categories = ServiceContainerFactory.make(Category.class);
                 }

                 @Override
                 protected Field<?> createField(final String pid) {
                     if (pid.equals("parentCategory")) {
                         final ComboBox combo = new ComboBox(pid);
                         combo.setContainerDataSource(categories);
                         combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
                         combo.setItemCaptionPropertyId("code");
                         combo.setFilteringMode(FilteringMode.CONTAINS);
                         combo.setImmediate(true);
                         combo.setConverter(new SingleSelectConverter(combo));
                         fieldGroup.bind(combo, pid);
                         return combo;
                     } else
                         return super.createField(pid);
                 }
             }, "main");
        	 
		return editorWindow;
        }
        
        @Override
        public void init() {
            super.init();
            container.addNestedContainerProperty("parentCategory.*");
            setPropertyToShow(new String[] { "parentCategory.code", "parentCategory.description" });
            entityList.setConverter("parentCategory.description", new MultilingualStringConvert());
            setParentProperty("childProduct");

           
        }



        @Override
        public EntityItem<CategoryProductRelation> createEntityItem() {
            final CategoryProductRelation _entity = new CategoryProductRelation();
            _entity.setChildProduct(parent.getEntity());
            return container.createEntityItem(_entity);
        }
    	};
    
    }

    private  MasterDetailTable<Price, AbstractProduct> buildPriceTab(){
    	
    	return new MasterDetailTable<Price, AbstractProduct>(Price.class) {
            private static final long serialVersionUID = 7566839007224552531L;

            @Override
            public EntityFormWindow<Price> buildEntityWindow() {
            	EntityFormWindow<Price> editorWindow = new EntityFormWindow<Price>();
                editorWindow.addTab(new EntityForm<Price>(Price.class) {

                    @Override
                    public void init() {
                        super.init();
                        setDisplayProperties(new String[] { "currentPrice", "ctentryCost", "productPrice",
                                "validFrom", "validTo" });
                    }

                }, "main");
            	return editorWindow;
            }
            @Override
            public void init() {
                super.init();
                container.addNestedContainerProperty("currency.*");
                setPropertyToShow(new String[] { "currentPrice", "ctentryCost", "productPrice", "currency.isoCode",
                        "validFrom", "validTo" });
                setParentProperty("product");
            }


            @Override
            public EntityItem<Price> createEntityItem() {
                final Price _entity = new Price();
                _entity.setProduct(parent.getEntity());
                return container.createEntityItem(_entity);
            }
        };
    }
    
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "description" });
        entityList.setConverter("description", new MultilingualStringConvert());
    }

	
}
