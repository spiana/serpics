package org.springframework.data.jpa.repository.support;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.serpics.core.Engine;
import com.serpics.core.data.InterceptorManager;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.core.data.SerpicsJpaRepository;
import com.serpics.core.session.SessionContext;

@Repository
@Transactional(readOnly = true)
public class CustomJpaRepository<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID>  implements JpaSpecificationExecutor<T>, SerpicsJpaRepository {

	
	private RepositoryInitializer initializer;
	private EntityManager em;
	private EntityInformation< T, ?> entityInformation;
	private final InterceptorManager<T> interceptorMapping;
	private Engine<SessionContext> engine;
	
	public CustomJpaRepository(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.em = entityManager;
		interceptorMapping = new InterceptorManager<T>();
		
	}
	public CustomJpaRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		interceptorMapping = new InterceptorManager<T>();
	}

	 protected TypedQuery<Long> getCountQuery(Specification<T> spec){
		return super.getCountQuery(getMergedSpecification(spec));
	 }
	 
	 private Specification<T> getMergedSpecification(Specification<T> spec) {
		 
		 List<Specification> defaultSpecs = initializer
					.getSpecificationForClass(getDomainClass());

			if (defaultSpecs != null)
				if (spec != null){
					spec = Specifications.where(spec);
					for (Specification specification : defaultSpecs) {
						spec = ((Specifications<T>) spec).and(specification);
					}	
				}else{
					if (defaultSpecs.size() == 1)
						spec = defaultSpecs.get(0);
					else{
						spec = Specifications.where(defaultSpecs.get(0));
						for (int x=1 ; x < defaultSpecs.size() ; x++)
							spec =((Specifications<T>) spec).and(defaultSpecs.get(x));
					}
						
				}
			return spec;
	 }
	
	@Override
	public void setRepositoryIniziatializer(RepositoryInitializer inizializer) {
		this.initializer = inizializer;
	}

	@Override
	public void setEngine(Engine<SessionContext> engine) {
		this.engine = engine;
	}

	@Transactional
	@Override
	public <S extends T> S save(S entity) {
		if (entityInformation.isNew(entity))
			interceptorMapping.performBeforeSaveInterceptor(entity);
		
		entity = super.save(entity);
		interceptorMapping.performAfterSaveInterceptor(entity);
		return entity;
	}

	protected TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getDomainClass());


		
		spec = getMergedSpecification(spec);

		Root<T> root = applySpecificationToCriteria(spec, query);
		query.select(root);

		if (sort != null) {
			query.orderBy(QueryUtils.toOrders(sort, root, builder));
		}

		return applyRepositoryMethodMetadata(this.em.createQuery(query));
	}

	private TypedQuery<T> applyRepositoryMethodMetadata(TypedQuery<T> query) {
		if (getRepositoryMethodMetadata() == null) {
			return query;
		}

		LockModeType type = getRepositoryMethodMetadata().getLockModeType();
		TypedQuery toReturn = (type == null) ? query : query.setLockMode(type);

		applyQueryHints(toReturn);

		return toReturn;
	}

	private void applyQueryHints(Query query) {
		for (Map.Entry hint : getQueryHints().entrySet())
			query.setHint((String) hint.getKey(), hint.getValue());
	}

	private <S> Root<T> applySpecificationToCriteria(Specification<T> spec,
			CriteriaQuery<S> query) {
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
}
