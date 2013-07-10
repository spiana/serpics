package com.serpics.catalog.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.serpics.catalog.persistence.Catalog;
import com.serpics.catalog.persistence.Category;
import com.serpics.catalog.persistence.CategoryRelation;
import com.serpics.catalog.persistence.Ctentry;
import com.serpics.catalog.persistence.CtentryRelation;
import com.serpics.catalog.persistence.CtentryRelationPK;
import com.serpics.catalog.persistence.AbstractProduct;
import com.serpics.catalog.persistence.dao.CatalogFactory;
import com.serpics.core.datatype.CatalogRelType;
import com.serpics.core.datatype.ProductType;
import com.serpics.core.persistence.dao.jpa.AbstractFactory;

@Repository("catalogFactory")
public class CatalogFactoryImpl extends AbstractFactory implements CatalogFactory {
	private static transient Logger logger = LoggerFactory.getLogger(CatalogFactoryImpl.class);

	
	@SuppressWarnings("unchecked")
	public List<Category> fetchRootCategory(Long catalogId) {
	
		String query = "SELECT * FROM catalog.ctentry_relation r " +
		"right outer join catalog.ctentry c on r.ctentry_id_child = c.ctentry_id" +
		" join catalog.category g on c.ctentry_id=g.ctentry_id"+
		" where c.ctentry_Type=0 and r.ctentry_id_child is null" +
		" and c.catalog_id=:catalogId";
			
		Query q = getEntityManager().createNativeQuery(query , Category.class);
		q.setParameter("catalogId", getCatalog(catalogId));
		return q.getResultList();
	}
	
	@Override
	public List<Category> fetchCategory(Long catalogId, Long parentId) {
		Catalog c = (Catalog) find(Catalog.class, catalogId);
		String query = "from ";
		return null;
	}


	@Override
	public List<AbstractProduct> fetchProduct(Long catalogId, Long categoryId, boolean onlyPublished) {
		String query = null;
		if (categoryId != null){
			query= "select p.* from  Ctentryrel r " +
					"join product c on r.ctentry_id_child = c.ctentry_id " +
					"  where c.catalog_id = :catalogId and relation_type=1 " +
					"and ctentry_id_parent=:categoryId and published in (:published)";
		}else{
			query = "select p.* from products "+
					"where c.catalogId= := catalogId published in (:published)";
		}
		
		ArrayList<Integer> valid = new ArrayList<Integer>();;
		valid.add(1);
		
		if (!onlyPublished)
			valid.add(0);
		
		Query q = getEntityManager().createNativeQuery(query , AbstractProduct.class);
		q.setParameter("published", valid.toArray() );
		q.setParameter("catalogId" , catalogId);

		if (categoryId != null)
			q.setParameter("categoryId", categoryId);
		
		@SuppressWarnings("unchecked")
		List<AbstractProduct> products = q.getResultList();
		
		return  products;
	}

	@Override
	public AbstractProduct createCatalogItem( AbstractProduct p,  Catalog catalog , AbstractProduct parent) {
		p.setCatalog(catalog);
		p.setProductType(ProductType.ITEM);
		persist(p);
		
		if (parent != null){
			makeCatalogReference(parent, p, CatalogRelType.PRODUCT_CHILD, 0);
		}
		
		return p ;
	}

	
	@Override
	public Category createCatalogCategory(Category c, String URL, Category parent , double sequence, Catalog catalog) {
		c.setCatalog(catalog);
		c.setUrl(URL);
		c.setUuid(UUID.randomUUID().toString());
		c.setUpdated(new Date());
		c.setCreated(new Date());
		
		persist(c);
		
		if (parent != null){
			makeCatalogReference(parent, c, CatalogRelType.CATEGORY_CHILD, 0);
		}
		return c;
	}
	
	@Override
	public Category createCatalogCategory(Category c, String URL, Category parent , Catalog catalog) {
		return createCatalogCategory( c, URL, parent, 0, catalog);
	}
	
	private void makeCatalogReference(Ctentry parent , Ctentry child , int type , double sequence){
		CtentryRelation crel= null;
		
		switch (type) {
		case CatalogRelType.CATEGORY_CHILD:
			crel = new CategoryRelation();
			break;

		default:
			break;
		} 
		crel.setRelationType(type);
		CtentryRelationPK id = new CtentryRelationPK(parent.getCtentryId(), child.getCtentryId());
		crel.setId(id);
		crel.setSequence(sequence);
		persist(crel);
		
	}

	@Override
	public AbstractProduct createCatalogProduct(AbstractProduct p, Catalog catalog) {
		p.setCatalog(catalog);
		p.setProductType(ProductType.PRODUCT);
		persist(p);
		return 	p ;
	}
	
	@Override
	public AbstractProduct createCatalogPackage(AbstractProduct p, Catalog catalog) {
		p.setCatalog(catalog);
		p.setProductType(ProductType.PACKAGE);
		persist(p);
		return 	p ;
	}


	@Override
	public Catalog getCatalog(long catalogId) {
		return (Catalog) find(Catalog.class , catalogId);
	}
	
	public void catalogDOM(Long catalogId){
		
	}
}
