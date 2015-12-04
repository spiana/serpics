package com.serpics.vaadin.ui.catalog;

import com.serpics.base.MultiValueField;
import com.serpics.catalog.data.model.AbstractProduct;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Feature;
import com.serpics.catalog.data.model.FeatureValues;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.repositories.CategoryRepository;
import com.serpics.stereotype.VaadinComponent;
import com.serpics.vaadin.jpacontainer.ServiceContainerFactory;
import com.serpics.vaadin.ui.EntityFormWindow;
import com.serpics.vaadin.ui.MasterDetailTable;
import com.serpics.vaadin.ui.MasterForm;
import com.serpics.vaadin.ui.MasterTable;
import com.serpics.vaadin.ui.converters.MultilingualFieldConvert;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
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

    private MasterForm<Product> buildMainTab(){
    	return new MasterForm<Product>(Product.class) {
            @Override
            public void init() {
                super.init();
                setDisplayProperties(new String[]{"code" ,"name","description","buyable","featureModel" });
                setReadOnlyProperties(new String[] { "created", "updated" , "uuid"});
            }
        };
    }
    
    
    @Override
    public EntityFormWindow<Product> buildEntityWindow() {
    	 EntityFormWindow<Product> editorWindow = new EntityFormWindow<Product>();
    	 
    	 editorWindow.addTab(buildMainTab(), "main");
    	 editorWindow.addTab(buildPriceTab(), "prices");
    	 
    	 editorWindow.addTab(buildCategoriesTab(), "categories");
    	
    	 
    	 editorWindow.addTab(new MasterDetailTable<FeatureValues , AbstractProduct>(FeatureValues.class) {
    		@Override
    		public void setParentEntity(EntityItem<AbstractProduct> parent) {
    			super.setParentEntity(parent);
    		}
    		@Override
    		public void init() {
    			super.init();
    			setParentProperty("featureValues");
    		}
    		
    		
    		 @Override
    		public EntityFormWindow<FeatureValues> buildEntityWindow() {
    			 
    			 EntityFormWindow<FeatureValues> w =  new EntityFormWindow<FeatureValues>();
    			
    			
    			
    			w.addTab(new MasterForm<FeatureValues>(FeatureValues.class) {
    				@Override
    				public void init() {
    					super.init();
    					setDisplayProperties(new String[]{"feature" , "value"});
    				}
    				private void addvalueField(){
    	    			Field <?>  f = fieldGroup.getField("value");
    	    			if(f != null){
    	    				fieldGroup.unbind(f);
    	    				removeComponent(f);
    	    			}
    	    	
    	    			addComponent(super.createField("value"));
    	    				
    				}
    			
    				@Override
    				protected Field<?> createField(String pid) {
    					if (pid.equals("feature")){
    								 final JPAContainer<Feature> features=ServiceContainerFactory.make(Feature.class);
    								 
    								 features.addContainerFilter(new Compare.Equal("featureModel" , masterEntity.getfeautureModel()));
									final ComboBox combo = new ComboBox(
											"feature");
									combo.setContainerDataSource(features);
									combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
									combo.setItemCaptionPropertyId("name");
									combo.setFilteringMode(FilteringMode.CONTAINS);
									combo.setImmediate(true);
									combo.setConverter(new SingleSelectConverter(
											combo));
									fieldGroup.bind(combo, "feature");
									
									combo.addValueChangeListener(new ValueChangeListener() {
										
										@Override
										public void valueChange(ValueChangeEvent event) {
											EntityItem<Feature> feature = features.getItem(event.getProperty().getValue());
											
											MultiValueField f = (MultiValueField) entityItem.getItemProperty("value").getValue();
											
											if (f != null)
												f.setAttributeType(feature.getEntity().getType());
											
												// entityItem.getItemProperty("value").setValue(feature.getEntity().getType());
												addvalueField();
											
										}
									});
									return combo;
    					}else	
    						return super.createField(pid);
    				}
    			
				}, "main");
    			 
    			 return w;
    		}
    		
    		
		}, "features");
    	 
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
        	
        	editorWindow.addTab(new MasterForm<CategoryProductRelation>(CategoryProductRelation.class) {

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
            entityList.setConverter("parentCategory.description", new MultilingualFieldConvert());
            setParentProperty("categories");

           
        }

       
    	};
    
    }

    private  MasterDetailTable<Price, AbstractProduct> buildPriceTab(){
    	
    	return new MasterDetailTable<Price, AbstractProduct>(Price.class) {
            private static final long serialVersionUID = 7566839007224552531L;

            @Override
            public EntityFormWindow<Price> buildEntityWindow() {
            	EntityFormWindow<Price> editorWindow = new EntityFormWindow<Price>();
                editorWindow.addTab(new MasterForm<Price>(Price.class) {

                    @Override
                    public void init() {
                        super.init();
                        setDisplayProperties(new String[] { "precedence","currentPrice", "productCost", "productPrice",
                                "validFrom", "validTo" });
                    }

                }, "main");
            	return editorWindow;
            }
            @Override
            public void init() {
                super.init();
                container.addNestedContainerProperty("currency.*");
                setPropertyToShow(new String[] { "precedence" ,"currentPrice", "productCost", "productPrice", "currency.isoCode",
                        "validFrom", "validTo" });
                setParentProperty("prices");
            }
           
        };
    }
    
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "name","description" , "buyable" });
        entityList.setConverter("description", new MultilingualFieldConvert());
    }

	
}
