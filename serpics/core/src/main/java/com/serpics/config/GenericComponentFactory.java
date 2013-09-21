package com.serpics.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

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
		T hook = createComponentInstance();
		// ((AbstractHook)
		// hook).setSessionContext(commerceEngine.getCurrentContext());
		return hook;
	}

	public T createComponentInstance() {
		T ref = null;

		final String store = StoreScopeContextHolder.getCurrentStoreRealm();
		Class<?> impl = componetImpls.get(store);
		// if not found specific implementation use default
		if (impl == null)
			impl = componetImpls.get("default-store");

		try {
			ref = (T) impl.newInstance();
		} catch (Exception e) {
			logger.error("error", e);
		}
		;
		return ref;
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

}
