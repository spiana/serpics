package com.serpics.core.data;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class RepositoryImpl<Z, IT extends Serializable> extends SimpleJpaRepository<Z, IT> implements Repository<Z, IT> {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private final EntityManager entityManager;

    public RepositoryImpl(final Class<Z> domainClass, final EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
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

}
