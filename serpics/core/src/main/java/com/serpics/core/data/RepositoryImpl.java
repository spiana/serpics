package com.serpics.core.data;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;

@org.springframework.stereotype.Repository
@Transactional(readOnly=true)
public class RepositoryImpl<Z,  ID extends Serializable> extends CustomJpaRepository<Z, ID> 
			implements Repository<Z, ID> {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private final EntityManager entityManager;
    
 
    
    public RepositoryImpl(JpaEntityInformation<Z, ?> entityInformation , EntityManager entityManager) {
			super(entityInformation, entityManager);
		 this.entityManager = entityManager;
	}

	public RepositoryImpl(final Class<Z> domainClass, final EntityManager em ) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public void detach(final Z entity) {
        entityManager.detach(entity);

    }

    @Override
    public Class<Z> getDomainClass() {
    	return super.getDomainClass();
    }
    
    
    @Override
    public <T> Specification<T> makeSpecification(final T example) {
        return new Specification<T>() {
        	
        	private Predicate makePredicate ( final CriteriaBuilder cb , Predicate p , Root<T> root){
        				
        		
        		EntityType<T> et = root.getModel();
        		
        		try{
		                final Set<SingularAttribute<? super T, ?>> attrs = et.getSingularAttributes();
		                if (logger.isDebugEnabled())
		                	logger.debug("create specification for model {} with {} attributes", et.getName(), attrs.size());
		                for (final SingularAttribute<? super T, ?> singleAttribute : attrs) {
		                    final String name = singleAttribute.getName();
		                    final String javaName = singleAttribute.getJavaMember().getName();
		                    final String getter = "get" + javaName.substring(0, 1).toUpperCase() + javaName.substring(1);
		                    final Method m = example.getClass().getMethod(getter, (Class<?>[]) null);
		                    if (logger.isDebugEnabled())
		                        logger.debug("Invoke method [{}] , with result [{}]", m.getName(),
		                                m.invoke(example, (Object[]) null));
		
		                    if (m.invoke(example, (Object[]) null) != null) {
		                        p = cb.and(p, cb.equal(root.get(name), m.invoke(example, (Object[]) null)));
		                        if (logger.isDebugEnabled())
		                            logger.debug("add condition for attribute [{}] with value [{}]", name,
		                                    m.invoke(example, (Object[]) null));
		                    }
		
		                }
		            } catch (final NoSuchMethodException e) {
		                new RuntimeException(e);
		            } catch (final SecurityException e) {
		                new RuntimeException(e);
		            } catch (final IllegalAccessException e) {
		                new RuntimeException(e);
		            } catch (final IllegalArgumentException e) {
		                new RuntimeException(e);
		            } catch (final InvocationTargetException e) {
		                new RuntimeException(e);
		            }
		
		        	
		        	return p;	
        	}
        	
            @Override
            public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

                Predicate p = cb.conjunction();
                return makePredicate( cb, p, root);
                
            }

        };
    }
    

    @Override
    @Transactional
	public Z create(Z entity) {
		entity =  saveAndFlush(entity);
		return entity;
	}

	@Override
	@Transactional
	public Z update(Z entity) {
		return saveAndFlush(entity);
	}

	@Override
	@Transactional
	public Z refresh(Z entity) {
		 return getEntityManager().merge(entity);
	}
	
	private CommerceEngine engine;
	 
	
	@Override
	public CommerceSessionContext getCurrentContext() {
		Assert.notNull(this.engine , "engine must be set in a repository !");
		return engine.getCurrentContext();
	}
    

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Z findByUUID(final String uuid) {
		Assert.notNull(uuid);
		List<Z> entities = findAll(new Specification<Z>() {
			@Override
			public Predicate toPredicate(Root<Z> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(root.get("uuid"), uuid) ;
			}
			
		}) ;
		Assert.state(entities.size() == 1, String.format("found more than object for UUID %s" , uuid));
    	if (!entities.isEmpty())
            return entities.get(0);
        else
            return null;
	}
	
//	@Override
//	public Z findById(final Long id) {
//		Assert.notNull(id);
//		List<Z> entities = findAll(new Specification<Z>() {
//			@Override
//			public Predicate toPredicate(Root<Z> root,
//					CriteriaQuery<?> cq, CriteriaBuilder cb) {
//				return cb.equal(root.get("id"), id) ;
//			}
//			
//		}) ;
//		Assert.state(entities.size() == 1, String.format("found more than object for ID %l" , id));
//    	if (!entities.isEmpty())
//            return entities.get(0);
//        else
//            return null;
//	}
}
