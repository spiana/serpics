package com.serpics.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import com.serpics.core.AbstractAutowiringFactoryBean;
import com.serpics.core.scope.StoreScopeContextHolder;

public class GenericComponentFactory<T> extends AbstractAutowiringFactoryBean<T> implements InitializingBean {

	static final Logger logger = LoggerFactory.getLogger(GenericComponentFactory.class);

	private final Map<String, Class<?>> componetImpls;

	private final Class<?> objectType;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericComponentFactory(Class<?> objectType, Map componentImpls) {
		this.objectType = objectType;
		this.componetImpls = componentImpls;
	}

	@Override
	protected T doCreateInstance() {
		T component = createComponentInstance();
		return component;
	}

	@SuppressWarnings("unchecked")
	public T createComponentInstance() {
		T ref = null;

		final String store = StoreScopeContextHolder.getCurrentStoreRealm();
		Class<?> impl = componetImpls.get(store);
		// if not found specific implementation use default
		if (impl == null)
			impl = componetImpls.get("default-store");
	
		if(impl == null){
			// no service implementation for "default-store"
			throw new BeanCreationException("default implementation not found  !" );
		}
		
		try {
			ref = (T) impl.newInstance();
		} catch (InstantiationException e) {
			throw new BeanCreationException("Error creating service istance !", e);
		} catch (IllegalAccessException e) {
			throw new BeanCreationException("Error creating service istance !", e);
		}
	
		
		return ref;
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

}
