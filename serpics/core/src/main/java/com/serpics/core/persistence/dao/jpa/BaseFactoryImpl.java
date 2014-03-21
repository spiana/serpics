package com.serpics.core.persistence.dao.jpa;

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
import org.springframework.stereotype.Repository;

import com.serpics.core.persistence.dao.BaseFactory;

@Repository
@Deprecated
public abstract class BaseFactoryImpl  implements BaseFactory {

    @PersistenceContext(unitName="serpics")
    protected EntityManager em ;

    public EntityManager getEntityManager(){
        return em;
    }

    @Override
    public void delete(final Object entity) throws DataAccessException {
        em.remove(entity);
    }

    @Override
    public Object merge(final Object entity) throws DataAccessException {
        return em.merge(entity);
    }
    @Override
    public Object persist(final Object entity) throws DataAccessException {
        em.persist(entity);
        return  entity;
    }

    @Override
    public <T> Object find(final Class<T> entityClass, final Object id) {
        return em.find(entityClass , id);
    }
    @Override
    public <T> List<T> findByExample(final Class<T> clazz , final T example) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchMethodException, InvocationTargetException {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(clazz);
        final Root<T> r = cq.from(clazz);
        Predicate p = cb.conjunction();
        final Metamodel mm = getEntityManager().getMetamodel();
        final EntityType<T> et = mm.entity(clazz);
        final Set<Attribute<? super T, ?>> attrs = et.getAttributes();
        for (final Attribute<? super T, ?> a: attrs) {
            final String name = a.getName();
            final String javaName = a.getJavaMember().getName();
            final String getter = "get" + javaName.substring(0,1).toUpperCase() + javaName.substring(1);
            final Method m = clazz.getMethod(getter, (Class<?>[]) null);
            if (m.invoke(example, (Object[]) null) !=  null)
                p = cb.and(p, cb.equal(r.get(name), m.invoke(example, (Object[]) null)));
        }
        cq.select(r).where(p);
        final TypedQuery<T> query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }




}
