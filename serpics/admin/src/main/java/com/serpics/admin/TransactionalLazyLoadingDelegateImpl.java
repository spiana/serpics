package com.serpics.admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpics.catalog.persistence.Category;

@Component
@Scope("prototype")
public class TransactionalLazyLoadingDelegateImpl implements TransactionalLazyLoadingDelegate {

	
	@PersistenceContext
	private EntityManager em;
	
	private SerpicsHibernateLazyLoadingDelegate caller;
	
    public <E> Object lazilyLoadPropertyValue(E entity, String prop) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> q = cb.createQuery();
        Root<? extends Object> root = q.from(entity.getClass());
        q.select(root.get(prop));
//        q.where(cb.equal(root.get("ctentryId"), cb.literal(caller.tryGetEntityId(entity))));
        Category c = (Category) entity;
        q.where(cb.equal(root.get("ctentryId"),c.getCtentryId() ));
        Query query = em.createQuery(q);
        return query.getResultList();
    }

	public SerpicsHibernateLazyLoadingDelegate getCaller() {
		return caller;
	}

	@Override
	public void setCaller(SerpicsHibernateLazyLoadingDelegate caller) {
		this.caller = caller;
	}
}
