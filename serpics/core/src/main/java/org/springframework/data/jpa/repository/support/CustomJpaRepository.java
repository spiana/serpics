package org.springframework.data.jpa.repository.support;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.Engine;
import com.serpics.core.data.InterceptorManager;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.core.data.SerpicsJpaRepository;
import com.serpics.core.session.SessionContext;

@Repository
@Transactional(readOnly=true)
public class CustomJpaRepository<T, ID extends Serializable>
  implements JpaRepository<T, ID>, JpaSpecificationExecutor<T> , SerpicsJpaRepository
{
  private final JpaEntityInformation<T, ?> entityInformation;
  private final EntityManager em;
  private final PersistenceProvider provider;
  private LockMetadataProvider lockMetadataProvider;

  private final InterceptorManager<T> interceptorMapping;
  
  private  RepositoryInitializer initializer;
  
	@Override
	public void setRepositoryIniziatializer(RepositoryInitializer inizializer) {
		this.initializer = inizializer;
	}
  
	private Engine<SessionContext> engine;
	 
	@Override
  public void setEngine(Engine<SessionContext> engine) {
		this.engine = engine;
	}

public CustomJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager)
  {
    Assert.notNull(entityInformation);
    Assert.notNull(entityManager);

    this.entityInformation = entityInformation;
    this.em = entityManager;
    this.provider = PersistenceProvider.fromEntityManager(entityManager);
    this.interceptorMapping = new InterceptorManager<T>();
  }

  public CustomJpaRepository(Class<T> domainClass, EntityManager em)
  {
    this(JpaEntityInformationSupport.getMetadata(domainClass, em), em);
  }

  public void setLockMetadataProvider(LockMetadataProvider lockMetadataProvider)
  {
    this.lockMetadataProvider = lockMetadataProvider;
  }

  private Class<T> getDomainClass() {
    return this.entityInformation.getJavaType();
  }

  private String getDeleteAllQueryString() {
    return QueryUtils.getQueryString("delete from %s x", this.entityInformation.getEntityName());
  }

//  private String getCountQueryString()
//  {
//    String countQuery = String.format("select count(%s) from %s x", new Object[] { this.provider.getCountQueryPlaceholder(), "%s" });
//    return QueryUtils.getQueryString(countQuery, this.entityInformation.getEntityName());
//  }

  @Transactional
  public void delete(ID id)
  {
    Assert.notNull(id, "The given id must not be null!");

    if (!(exists(id))) {
      throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!", new Object[] { this.entityInformation.getJavaType(), id }), 1);
    }

    delete(findOne(id));
  }

  @Transactional
  public void delete(T entity)
  {
    Assert.notNull(entity, "The entity must not be null!");
    this.em.remove((this.em.contains(entity)) ? entity : this.em.merge(entity));
  }

  @Transactional
  public void delete(Iterable<? extends T> entities)
  {
    Assert.notNull(entities, "The given Iterable of entities not be null!");

    for (Iterator<? extends T> i$ = entities.iterator(); i$.hasNext(); ) { T entity =  i$.next();
      delete(entity);
    }
  }

  @Transactional
  public void deleteInBatch(Iterable<T> entities)
  {
    Assert.notNull(entities, "The given Iterable of entities not be null!");

    if (!(entities.iterator().hasNext())) {
      return;
    }

    QueryUtils.applyAndBind(QueryUtils.getQueryString("delete from %s x", this.entityInformation.getEntityName()), entities, this.em).executeUpdate();
  }

  @Transactional
  public void deleteAll()
  {
    for (Iterator<T> i$ = findAll().iterator(); i$.hasNext(); ) { T element = i$.next();
      delete(element);
    }
  }

  @Transactional
  public void deleteAllInBatch()
  {
    this.em.createQuery(getDeleteAllQueryString()).executeUpdate();
  }

  public T findOne(ID id)
  {
    Assert.notNull(id, "The given id must not be null!");
    return this.em.find(getDomainClass(), id);
  }

  public boolean exists(ID id)
  {
    Assert.notNull(id, "The given id must not be null!");

    if (this.entityInformation.getIdAttribute() != null)
    {
      String placeholder = this.provider.getCountQueryPlaceholder();
      String entityName = this.entityInformation.getEntityName();
      Iterable<String> idAttributeNames = this.entityInformation.getIdAttributeNames();
      String existsQuery = QueryUtils.getExistsQueryString(entityName, placeholder, idAttributeNames);

      TypedQuery query = this.em.createQuery(existsQuery, Long.class);

      if (this.entityInformation.hasCompositeId()) {
        for (String idAttributeName : idAttributeNames)
          query.setParameter(idAttributeName, this.entityInformation.getCompositeIdAttributeValue(id, idAttributeName));
      }
      else {
        query.setParameter((String)idAttributeNames.iterator().next(), id);
      }

      return (((Long)query.getSingleResult()).longValue() == 1L);
    }
    return (findOne(id) != null);
  }

  public List<T> findAll()
  {
    return getQuery(null, (Sort)null).getResultList();
  }

  public List<T> findAll(Iterable<ID> ids)
  {
    return getQuery(new Specification<T>() {
      public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path path = root.get(CustomJpaRepository.this.entityInformation.getIdAttribute());
        return path.in(new Expression[] { cb.parameter(Iterable.class, "ids") });
      }
    }
    , (Sort)null).setParameter("ids", ids).getResultList();
  }

  public List<T> findAll(Sort sort)
  {
    return getQuery(null, sort).getResultList();
  }

  public Page<T> findAll(Pageable pageable)
  {
    if (null == pageable) {
      return new PageImpl(findAll());
    }

    return findAll(null, pageable);
  }

  public T findOne(Specification<T> spec)
  {
    try
    {
      return getQuery(spec, (Sort)null).getSingleResult(); } catch (NoResultException e) {
    }
    return null;
  }

  public List<T> findAll(Specification<T> spec)
  {
    return getQuery(spec, (Sort)null).getResultList();
  }

  public Page<T> findAll(Specification<T> spec, Pageable pageable)
  {
    TypedQuery query = getQuery(spec, pageable);
    return ((pageable == null) ? new PageImpl(query.getResultList()) : readPage(query, pageable, spec));
  }

  public List<T> findAll(Specification<T> spec, Sort sort)
  {
    return getQuery(spec, sort).getResultList();
  }

  
  public long count(){
		Specification<T> defaultSpec = new Specification<T>() {
			 @Override
			public Predicate toPredicate(Root<T> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {
				return paramCriteriaBuilder.isNotNull(paramRoot.get("uuid"));
			}
		};
    return count(defaultSpec);
  }

  public long count(Specification<T> spec)
  {
    return ((Long)getCountQuery(spec).getSingleResult()).longValue();
  }

  @Transactional
  public <S extends T> S save(S entity)
  {
    if (this.entityInformation.isNew(entity)) {
      interceptorMapping.performBeforeCreateInterceptor(entity);	
      this.em.persist(entity);
      interceptorMapping.performAfterCreateInterceptor(entity);
      return entity;
    }
    return this.em.merge(entity);
  }

  @Transactional
  public T saveAndFlush(T entity)
  {
    T result = save(entity);
    flush();

    return result;
  }

  @Transactional
  public <S extends T> List<S> save(Iterable<S> entities)
  {
    List result = new ArrayList();

    if (entities == null) {
      return result;
    }

    for (Iterator i$ = entities.iterator(); i$.hasNext(); ) { T entity = (T) i$.next();
      result.add(save(entity));
    }

    return result;
  }

  @Transactional
  public void flush()
  {
    this.em.flush();
  }

  private Page<T> readPage(TypedQuery<T> query, Pageable pageable, Specification<T> spec)
  {
    query.setFirstResult(pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());

    Long total = QueryUtils.executeCountQuery(getCountQuery(spec));
    List content = (total.longValue() > pageable.getOffset()) ? query.getResultList() : Collections.emptyList();

    return new PageImpl(content, pageable, total.longValue());
  }

  private TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable)
  {
    Sort sort = (pageable == null) ? null : pageable.getSort();
    return getQuery(spec, sort);
  }

  private TypedQuery<T> getQuery(Specification<T> spec, Sort sort)
  {
    CriteriaBuilder builder = this.em.getCriteriaBuilder();
    CriteriaQuery<T> query = builder.createQuery(getDomainClass());

    Specification<T> defaultSpec = initializer.getSpecificationForClass(getDomainClass());
    
    if (defaultSpec != null)
    	if(spec != null)
    		spec = where(defaultSpec).and(spec);
    	else
    		spec = defaultSpec;
    
    Root<T> root = applySpecificationToCriteria(spec, query);
    query.select(root);

    if (sort != null) {
      query.orderBy(QueryUtils.toOrders(sort, root, builder));
    }

    return applyLockMode(this.em.createQuery(query));
  }

  private TypedQuery<Long> getCountQuery(Specification<T> spec)
  {
	 Specification<T> defaultSpec = initializer.getSpecificationForClass(getDomainClass()) ;
	 
	 if(defaultSpec != null)
		 if(spec != null)
			 spec = where(defaultSpec).and(spec);
		 else
			 spec = defaultSpec;	
	 
	  
    CriteriaBuilder builder = this.em.getCriteriaBuilder();
    CriteriaQuery query = builder.createQuery(Long.class);

    Root root = applySpecificationToCriteria(spec, query);

    if (query.isDistinct())
      query.select(builder.countDistinct(root));
    else {
      query.select(builder.count(root));
    }

    return this.em.createQuery(query);
  }

  private <S> Root<T> applySpecificationToCriteria(Specification<T> spec, CriteriaQuery<S> query)
  {
    Assert.notNull(query);
    Root root = query.from(getDomainClass());

    if (spec == null) {
      return root;
    }

    CriteriaBuilder builder = this.em.getCriteriaBuilder();
    Predicate predicate = spec.toPredicate(root, query, builder);

    if (predicate != null) {
      query.where(predicate);
    }

    return root;
  }

  private TypedQuery<T> applyLockMode(TypedQuery<T> query)
  {
    LockModeType type = (this.lockMetadataProvider == null) ? null : this.lockMetadataProvider.getLockModeType();
    return ((type == null) ? query : query.setLockMode(type));
  }
}
