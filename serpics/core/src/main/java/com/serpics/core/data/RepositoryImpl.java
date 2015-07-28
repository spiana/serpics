package com.serpics.core.data;

import static org.springframework.data.jpa.domain.Specifications.where;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.commerce.core.CommerceEngine;
import com.serpics.commerce.session.CommerceSessionContext;

@Transactional(readOnly=true)
public class RepositoryImpl<Z, IT extends Serializable> extends SimpleJpaRepository<Z, IT> 
			implements Repository<Z, IT>{

    private static final Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private final EntityManager entityManager;
   
    private final InterceptorManager<Z> interceptorMapping;
    
    private  RepositoryInitializer initializer;
    
    private Class<Z> domainClass;
   
    
    public RepositoryImpl(final Class<Z> domainClass, final EntityManager em ) {
        super(domainClass, em);
        this.domainClass = domainClass;
        this.entityManager = em;
        this.interceptorMapping = new InterceptorManager<Z>();
    }

    @Override
    public void detach(final Z entity) {
        entityManager.detach(entity);

    }

    @Override
    public <T> Specification<T> makeSpecification(final T example) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {

                Predicate p = cb.conjunction();
                try {
                    final EntityType<T> et = root.getModel();
                    final Set<SingularAttribute<? super T, ?>> attrs = et.getSingularAttributes();
                    logger.info("create specification for model {} with {} attributes", et.getName(), attrs.size());
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

        };
    }
    
    @Override
    public Page<Z> findAll(final Pageable page) {
    	if(getDefaultSpecification() != null)
    		return super.findAll(getDefaultSpecification(), page);
    	else
    		return super.findAll(page);
    }

    @Override
    public List<Z> findAll() {
        return getDefaultSpecification() != null? super.findAll(getDefaultSpecification()): super.findAll();
    }
   
    @Override
    public List<Z> findAll(final Specification<Z> spec, final Sort sort) {
    		if(spec == null && sort == null){
    			return getDefaultSpecification() == null ?super.findAll():
                	super.findAll(getDefaultSpecification());
    		}
    		if(sort== null){
    			return getDefaultSpecification() == null ?super.findAll(where(spec)):
                	super.findAll(where(spec).and(getDefaultSpecification()));
    		}
    		if (spec== null){
    			return getDefaultSpecification() == null ?super.findAll(sort):
                	super.findAll(getDefaultSpecification(), sort);
    		}		
    			
            return getDefaultSpecification() == null ?super.findAll(where(spec), sort):
            	super.findAll(where(spec).and(getDefaultSpecification()), sort);
    	
    }

    @Override
    public List<Z> findAll(Specification<Z> spec) {
    	if (spec == null){
    		return getDefaultSpecification() == null ?super.findAll():
            	super.findAll(getDefaultSpecification());
    	}
    		
    	return getDefaultSpecification() == null ? super.findAll(spec) :
    		super.findAll(where(spec).and(getDefaultSpecification()));
    }
    
    @Override
    public List<Z> findAll(Sort sort) {
    	
    	return getDefaultSpecification() ==  null ?super.findAll(sort) :
    		super.findAll(getDefaultSpecification() , sort);
    }
    
    @Override
    public Page<Z> findAll(Specification<Z> spec, Pageable pageable) {
    	return  getDefaultSpecification() == null ?super.findAll(spec, pageable) :
    		super.findAll(where(spec).and(getDefaultSpecification()), pageable);
    }
   
    
   @Override
	public Z findOne(Specification<Z> arg0) {
		return getDefaultSpecification() == null ?super.findOne(arg0) : super.findOne(where(arg0).and(getDefaultSpecification()));
	}
   
   @Override
   public Z findOne(Specification<Z> spec , final Sort sort , int index ) {
	   final PageRequest singleResultPage = new PageRequest(index, 1, sort);
       final Page<Z> l = super.findAll(spec, singleResultPage);
       if (!l.getContent().isEmpty())
           return l.getContent().get(0);
       else
           return null;
	}
   
    @SuppressWarnings("unchecked")
	@Override
	public Specification<Z> getDefaultSpecification() {
    		return initializer.getSpecificationForClass(this.domainClass);
	}

    @Override
    @Transactional
	public Z create(Z entity) {
		interceptorMapping.performBeforeCreateInterceptor(entity);
		entity =  saveAndFlush(entity);
		interceptorMapping.performAfterCreateInterceptor(entity);
		return entity;
	}

	@Override
	@Transactional
	public Z update(Z entity) {
		
		return saveAndFlush(entity);
		
		
	}

	@Override
	public Z refresh(Z entity) {
		 return getEntityManager().merge(entity);
	}
	
	private CommerceEngine engine;
	 
	@Override
	public void setEngine(CommerceEngine engine) {
		this.engine = engine;
	}

	@Override
	public CommerceSessionContext getCurrentContext() {
		Assert.notNull(this.engine , "engine must be set in a repository !");
		return engine.getCurrentContext();
	}
    
	@Override
	public void setRepositoryIniziatializer(RepositoryInitializer inizializer) {
		this.initializer = inizializer;
	}

	@Override
	public Class<?> getDomainClass() {
		return this.domainClass;
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
}
