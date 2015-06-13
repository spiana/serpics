package com.serpics.vaadin.jpacontainer.provider;

import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.Type;

import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityServiceProvider;

public class ServiceContainerFactory {

	@SuppressWarnings({ "unchecked"})
	public static <T> JPAContainer<T> make(final Class<T> entityClass) {
		final JPAContainer<T> cont = new JPAContainer<T>(entityClass);
		final CachingLocalEntityServiceProvider<T> provider = new CachingLocalEntityServiceProvider<T>(
				entityClass);
		provider.setCacheEnabled(true);
		cont.setEntityProvider(provider);
		provider.setRepository(RepositoryInitializer.getInstance()
				.getRepositoryForEntity(entityClass));

		return cont;
	}

	public static Class<?> detectReferencedType(EntityManagerFactory emf,
			Object propertyId, Class masterEntityClass) {
		Class<?> referencedType = null;
		Metamodel metamodel = emf.getMetamodel();
		Set<EntityType<?>> entities = metamodel.getEntities();
		for (EntityType<?> entityType : entities) {
			Class<?> javaType = entityType.getJavaType();
			if (javaType == masterEntityClass) {
				Attribute<?, ?> attribute = entityType.getAttribute(propertyId
						.toString());

				PluralAttribute<?,?,?> pAttribute = (PluralAttribute<?, ?, ?>) attribute;
				Type<?> elementType = pAttribute.getElementType();
				referencedType = elementType.getJavaType();
				break;
			}
		}
		return referencedType;
	}
}
