package org.serpics.admin;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.dellroad.stuff.vaadin.SpringContextApplication;
import org.junit.Assert;
import org.junit.Test;
import org.serpics.admin.domain.Person;
import org.serpics.admin.ui.BasicCrudView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.Bundle;
import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.Product;
import com.serpics.catalog.repositories.AbstractProductRepository;
import com.serpics.catalog.repositories.BundleRepository;
import com.serpics.catalog.repositories.CatalogRepository;
import com.serpics.catalog.repositories.ProductRepository;
import com.serpics.catalog.services.CatalogService;
import com.serpics.core.CommerceEngine;
import com.serpics.core.SerpicsException;
import com.serpics.core.datatype.UserRegisterType;
import com.serpics.core.datatype.UserType;
import com.serpics.core.session.CommerceSessionContext;
import com.serpics.membership.persistence.PermanentAddress;
import com.serpics.membership.persistence.User;
import com.serpics.membership.persistence.UsersReg;
import com.serpics.membership.services.BaseService;
import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends SpringContextApplication {

	@PersistenceContext
	EntityManager em;
	
	
	@Resource
	BaseService baseService;

	@Resource
	CatalogService catalogService;

	@Autowired
	CommerceEngine ce;

	@Resource
	CatalogRepository catalogRepository;

	@Resource
	ProductRepository productRepository;
	@Resource
	BundleRepository bundleRepository;

	@Resource
	AbstractProductRepository abstractProductRepository;
	
	
	public static final String PERSISTENCE_UNIT = "serpics";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	class AutoCrudViews extends Window {
		
		public AutoCrudViews() {
			final HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
			Tree navTree = new Tree();
			navTree.addListener(new Property.ValueChangeListener() {
				@Override
				public void valueChange(ValueChangeEvent event) {
					BasicCrudView cv = (BasicCrudView) event.getProperty()
							.getValue();
					cv.refreshContainer();
					horizontalSplitPanel.setSecondComponent(cv);
				}
			});
			navTree.setSelectable(true);
			navTree.setNullSelectionAllowed(false);
			navTree.setImmediate(true);

			horizontalSplitPanel.setSplitPosition(200,
					HorizontalSplitPanel.UNITS_PIXELS);
			horizontalSplitPanel.addComponent(navTree);
			setContent(horizontalSplitPanel);

			// add a basic crud view for all entities known by the JPA
			// implementation, most often this is not desired and developers
			// should just list those entities they want to have editors for
//			Metamodel metamodel = JPAContainerFactory
//					.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT)
//					.getEntityManagerFactory().getMetamodel();
			
			Metamodel metamodel = em.getMetamodel();
			
			Set<EntityType<?>> entities = metamodel.getEntities();
			for (EntityType<?> entityType : entities) {
				Class<?> javaType = entityType.getJavaType();
				BasicCrudView view = new BasicCrudView(javaType,
						em);
				navTree.addItem(view);
				navTree.setItemCaption(view, view.getCaption());
				navTree.setChildrenAllowed(view, false);
				if(javaType == Person.class) {
					view.setVisibleTableProperties("firstName","lastName", "boss");
					view.setVisibleFormProperties("firstName","lastName", "phoneNumber", "street", "city", "zipCode", "boss");
				}

			}

			// select first entity view
			navTree.setValue(navTree.getItemIds().iterator().next());
		}
	}

	@Override
	protected void initSpringApplication(ConfigurableWebApplicationContext arg0) {

		setMainWindow(new AutoCrudViews());
		
	}


	
	
	@Test
	@Transactional
	public void test() throws SerpicsException {
		baseService.initIstance();
		CommerceSessionContext context = ce.connect("default-store", "superuser", "admin".toCharArray());
		Catalog catalog = new Catalog();
		catalog.setCode("default-catalog");
		catalog = catalogService.createCatalog(catalog);
		
		context.setCatalog(catalog);
		List<Catalog> l = catalogRepository.findAll();
		Assert.assertEquals(1, l.size());

		List<Catalog> _l = catalogRepository.findPublished();
		Assert.assertEquals(1, _l.size());


		Category category = new Category();
		category.setCode("main");
		category = catalogService.createCategory(category, null);
		
		Category category1 = new Category();
		category1.setCode("main1");
		category1 = catalogService.createCategory(category1, category);
		
		
		Product p = new Product();
		p.setCode("test-sku");
		p.setCatalog(catalog);
		p.setBuyable(1);
		catalogService.createproduct(p);

		Bundle b = new Bundle();
		b.setCode("bundle-sku");
		b.setCatalog(catalog);
		b.setBuyable(1);
		catalogService.createproduct(b);

		Product p1 = new Product();
		p1.setCode("test-sku");
		p1.setCatalog(catalog);
		p1.setBuyable(1);
		

		Bundle b1 = new Bundle();
		b1.setCode("bundle-sku");
		b1.setCatalog(catalog);
		b1.setBuyable(1);
	//	b1.setPublished(1);

		Product p2 = productRepository.findOne(productRepository.makeSpecification(p1));
		Assert.assertNotNull(p2);
		Assert.assertEquals("test-sku", p2.getCode());

		Bundle b2 = bundleRepository.findOne(bundleRepository.makeSpecification(b1));
		Assert.assertNotNull(b2);
		Assert.assertEquals("bundle-sku", b2.getCode());

		
		AbstractProduct p3 = abstractProductRepository.findOne(abstractProductRepository.makeSpecification( (AbstractProduct) p1));
		Assert.assertNotNull(p3);
		Assert.assertEquals("test-sku", p3.getCode());
		
	
		
		catalogService.deleteCatalogEntry(p3);
		List<Category> l1 = catalogService.findRootCategory();
//		Assert.assertEquals(1, l1.size());
		List<Product> l2 = productRepository.findAll();
//		Assert.assertEquals(0, l2.size());
		List<AbstractProduct> l3 = abstractProductRepository.findAll();
//		Assert.assertEquals(1, l3.size());
		// catalogService.deleteCatalog(catalog);
		
	}
	
	
//	static {
//		EntityManager em = JPAContainerFactory
//				.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
//
//		long size = (Long) em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
//		if (size == 0) {
//			// create two Person objects as test data
//
//			em.getTransaction().begin();
//			Person boss = new Person();
//			boss.setFirstName("John");
//			boss.setLastName("Bigboss");
//			boss.setCity("Turku");
//			boss.setPhoneNumber("+358 02 555 221");
//			boss.setZipCode("20200");
//			boss.setStreet("Ruukinkatu 2-4");
//			em.persist(boss);
//
//			Person p = new Person();
//			p.setFirstName("Marc");
//			p.setLastName("Hardworker");
//			p.setCity("Turku");
//			p.setPhoneNumber("+358 02 555 222");
//			p.setZipCode("20200");
//			p.setStreet("Ruukinkatu 2-4");
//			p.setBoss(boss);
//			em.persist(p);
//
//			em.getTransaction().commit();
//		}
//
//	}

}
