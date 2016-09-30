package com.serpics.vaadin.smc.ui.catalog;

import javax.annotation.Resource;

import com.serpics.base.AvailableforType;
import com.serpics.base.MultiValueField;
import com.serpics.base.data.model.BaseAttribute;
import com.serpics.base.data.model.MultiValueAttribute;
import com.serpics.catalog.data.ProductType;
import com.serpics.catalog.data.model.Category;
import com.serpics.catalog.data.model.CategoryProductRelation;
import com.serpics.catalog.data.model.Ctentry;
import com.serpics.catalog.data.model.CtentryAttribute;
import com.serpics.catalog.data.model.Feature;
import com.serpics.catalog.data.model.FeatureValues;
import com.serpics.catalog.data.model.Price;
import com.serpics.catalog.data.model.Product;
import com.serpics.catalog.data.model.ProductVariant;
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
    
    @Resource
    private ProductVariantTable variantTable;
    
    
    class ProductEditWindow extends EntityFormWindow<Product>{
    	
    	
    	/* (non-Javadoc)
    	 * @see com.serpics.vaadin.ui.EntityFormWindow#initContent()
    	 */
    	@Override
    	public void initContent() {
    		super.initContent();
    		if (getEntityItem().getItemProperty("productType").getValue().equals(ProductType.SINGLE))
    			getTabSheet().removeTab(getTabSheet().getTab(getTabComponentCount()-1));
    	}
    	
    }
    
    
    public ProductTable() {
        super(Product.class);
    }

    private MasterForm<Product> buildMainTab(){
    	return new MasterForm<Product>(Product.class) {
            @Override
            public void init() {
                super.init();
                setDisplayProperties(new String[]{"code" ,"productType","name","description","buyable","status", "featureModel" , "brand" ,"primaryImage", "medias" , "weight" , "weightMeas", "taxcategory","created", "updated"});
                setReadOnlyProperties(new String[] { "created", "updated" , "uuid"});
            }
            
         };
    }
    
    
    
    
    @Override
    public EntityFormWindow<Product> buildEntityWindow() {
    	 EntityFormWindow<Product> editorWindow = new ProductEditWindow();
    	 
    	 editorWindow.addTab(buildMainTab(), "main");
    	 editorWindow.addTab(buildPriceTab(), "prices");
    	 editorWindow.addTab(buildCategoriesTab(), "categories");
    	 editorWindow.addTab(buildFeatureValueTab(), "features");
    	 
    	 editorWindow.addTab(buildVariantTab(), "variant");
    
    	 
    	 
    	 return editorWindow;
    }
   
    
    @Override
    public void init() {
        super.init();
        setPropertyToShow(new String[] { "code", "productType", "name","description" , "buyable" });
    }

    
    private MasterDetailTable<CategoryProductRelation, Product> buildCategoriesTab(){

    	return new MasterDetailTable<CategoryProductRelation, Product>(
                CategoryProductRelation.class){
    		
        private static final long serialVersionUID = -2478612011226738573L;

        private transient JPAContainer<Category> categories;

        @Override
		public EntityFormWindow<CategoryProductRelation> buildEntityWindow() {
			
        	EntityFormWindow<CategoryProductRelation> editorWindow = new EntityFormWindow<CategoryProductRelation>("CategoryProductRelation");
        	
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

    private  MasterDetailTable<Price, Product> buildPriceTab(){
    	
    	return new AbstractPriceTable<Product>() {
			private static final long serialVersionUID = 1319744867129217907L;
			};
    }
  
    
	private MasterDetailTable<FeatureValues, Product> buildFeatureValueTab(){
		
		return new MasterDetailTable<FeatureValues , Product>(FeatureValues.class) {
    		
    		@Override
    		public void init() {
    			super.init();
    			setParentProperty("featureValues");
    		}
    		
    		@Override
    		public void setParentEntity(EntityItem<Product> parent) {
    			super.setParentEntity(parent);
    			if (parent.getItemProperty("featureModel").getValue() == null)
    				buttonsEnabler(false, false, false);
    		}
    		
    		 @Override
    		public EntityFormWindow<FeatureValues> buildEntityWindow() {
    			 
    			 EntityFormWindow<FeatureValues> w =  new EntityFormWindow<FeatureValues>("featurevalue");
    			
    			
    			
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
    								 
    								 features.addContainerFilter(new Compare.Equal("featureModel" , parentEntity.getItemProperty("featureModel").getValue()));
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
    		
    		
		};
	}
	
	
private MasterDetailTable<ProductVariant, Product> buildVariantTab(){
	return new MasterDetailTable<ProductVariant, Product>(ProductVariant.class , "variants") {
		private static final long serialVersionUID = 4046128089106726731L;
		/* (non-Javadoc)
		 * @see com.serpics.vaadin.ui.MasterTable#init()
		 */
		@Override
		public void init() {
			super.init();
			setPropertyToShow(new String[]{"code" , "description"});
		}
		/* (non-Javadoc)
		 * @see com.serpics.vaadin.ui.MasterTable#buildEntityWindow()
		 */
		@Override
		public EntityFormWindow<ProductVariant> buildEntityWindow() {
			EntityFormWindow<ProductVariant> w = new VariantEditWindow();
			return w;
		}
	};
}
private MasterDetailTable<CtentryAttribute, ? extends Ctentry> buildProductAttributeTab(){
		
		return new MasterDetailTable<CtentryAttribute , Ctentry>(CtentryAttribute.class) {
    	
    		@Override
    		public void init() {
    			super.init();
    			setParentProperty("ctentryAttributes");
    			setPropertyToShow(new String[] {"baseAttribute.name","value", "sequence"});
    		}
    		
    		
    		 @Override
    		public EntityFormWindow<CtentryAttribute> buildEntityWindow() {
    			 EntityFormWindow<CtentryAttribute> w =  new EntityFormWindow<CtentryAttribute>("ctentryadddribute");
    			 w.addTab(new MasterForm<CtentryAttribute>(CtentryAttribute.class) {
    				@Override
    				public void init() {
    					super.init();
    					setDisplayProperties(new String[]{"baseAttribute" , "sequence","value"});
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
    					if (pid.equals("baseAttribute")){
    								 final JPAContainer<BaseAttribute> baseAttributeses=ServiceContainerFactory.make(BaseAttribute.class);
    								 
    								 baseAttributeses.addContainerFilter(new Compare.Equal("availablefor" ,AvailableforType.PRODUCT ));
									final ComboBox combo = new ComboBox(
											"baseAttribute");
									combo.setContainerDataSource(baseAttributeses);
									combo.setItemCaptionMode(ItemCaptionMode.PROPERTY);
									combo.setItemCaptionPropertyId("name");
									combo.setFilteringMode(FilteringMode.CONTAINS);
									combo.setImmediate(true);
									combo.setConverter(new SingleSelectConverter(combo));
									fieldGroup.bind(combo, "baseAttribute");
									
									combo.addValueChangeListener(new ValueChangeListener() {
										
										@Override
										public void valueChange(ValueChangeEvent event) {
											EntityItem<BaseAttribute> attribute = baseAttributeses.getItem(event.getProperty().getValue());
											
											MultiValueField f = (MultiValueField) entityItem.getItemProperty("value").getValue();
											if (f == null){
												f = new MultiValueAttribute();
												 entityItem.getItemProperty("value").setValue(f);
											}
												
											f.setAttributeType(attribute.getEntity().getAttributeType());
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
		};
	}
}

