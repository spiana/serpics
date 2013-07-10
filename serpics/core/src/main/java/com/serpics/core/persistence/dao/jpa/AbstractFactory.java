package com.serpics.core.persistence.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.jpa.JpaAccessor;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;

import com.mysema.query.apt.jpa.JPAAnnotationProcessor;
import com.mysema.query.hql.HQLQuery;
import com.mysema.query.hql.jpa.JPAQuery;
import com.serpics.core.persistence.dao.BaseFactory;

@Deprecated
@Repository
public abstract class AbstractFactory implements BaseFactory {


	@PersistenceContext(unitName="serpics")
	protected EntityManager em ;
	
	
	public EntityManager getEntityManager(){
		return em;
	}
	@Override
	public void delete(Object entity) throws DataAccessException {
		em.remove(entity);
	}

	@Override
	public Object merge(Object entity) throws DataAccessException {
		return em.merge(entity);
	}
	@Override
	public Object persist(Object entity) throws DataAccessException {
		em.persist(entity);
		 return  entity;
	}

	@Override
	public <T> Object find(Class<T> entityClass, Object id) {
		return em.find(entityClass , id);
	}

	public HQLQuery getHQLQuery(){
		return new JPAQuery(em);
	}
	
	public <T> List<T> findByExample(Class<T> clazz , T example) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> r = cq.from(clazz);
        Predicate p = cb.conjunction();
        Metamodel mm = getEntityManager().getMetamodel();
        EntityType<T> et = mm.entity(clazz);
        Set<Attribute<? super T, ?>> attrs = et.getAttributes();
        for (Attribute<? super T, ?> a: attrs) {
            String name = a.getName();
            String javaName = a.getJavaMember().getName();
            String getter = "get" + javaName.substring(0,1).toUpperCase() + javaName.substring(1);
            Method m = clazz.getMethod(getter, (Class<?>[]) null);
            if (m.getReturnType() != Set.class){ 
            	if (m.invoke(example, (Object[]) null) !=  null ) 
                	p = cb.and(p, cb.equal(r.get(name), m.invoke(example, (Object[]) null)));
            }
        }
        cq.select(r).where(p);
        
        TypedQuery<T> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }

	
	
	
}
