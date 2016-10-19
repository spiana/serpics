/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

import com.serpics.core.AbstractAutowiringFactoryBean;
import com.serpics.core.scope.StoreScopeContextHolder;

public class StoreComponentFactory<T> extends AbstractAutowiringFactoryBean<T> implements InitializingBean {

    static final Logger logger = LoggerFactory.getLogger(StoreComponentFactory.class);

    private final Map<String, Class<?>> componetImpls;



    @SuppressWarnings({ "rawtypes", "unchecked" })
    public StoreComponentFactory( final Map componentImpls) {
        this.componetImpls = componentImpls;
    }

    @Override
    protected T doCreateInstance() {
        final T component = createComponentInstance();
        return component;
    }

    @SuppressWarnings("unchecked")
    public T createComponentInstance() {
        T ref = null;

        final Class<?> impl = getImplementedObject();

        if(impl == null){
            // no service implementation for "default-store"
            throw new BeanCreationException("default implementation not found  !" );
        }

        try {
            ref = (T) impl.newInstance();
        } catch (final InstantiationException e) {
            throw new BeanCreationException("Error creating service istance !", e);
        } catch (final IllegalAccessException e) {
            throw new BeanCreationException("Error creating service istance !", e);
        }


        return ref;
    }

    @Override
    public Class<?> getObjectType() {
        final Class<?> obj = getImplementedObject(); 
        return obj != null ? obj :Object.class;
    }

    private Class<?> getImplementedObject(){

        final String store = StoreScopeContextHolder.getCurrentStoreRealm();

        if (store == null)
            return null;

        Class<?> impl = componetImpls.get(store);
        // if not found specific implementation use default
        if (impl == null)
            impl = componetImpls.get("default-store");

        return impl;

    }
}
