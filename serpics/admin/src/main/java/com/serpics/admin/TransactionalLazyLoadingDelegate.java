package com.serpics.admin;

import org.springframework.transaction.annotation.Transactional;

import com.vaadin.addon.jpacontainer.SerpicsHibernateLazyLoadingDelegate;

public interface TransactionalLazyLoadingDelegate {

	@Transactional
	public <E> Object lazilyLoadPropertyValue(E entity, String prop);

	public void setCaller(SerpicsHibernateLazyLoadingDelegate caller);
}
