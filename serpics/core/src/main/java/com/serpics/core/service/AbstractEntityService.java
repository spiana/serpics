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
package com.serpics.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.core.data.Repository;
import com.serpics.core.session.SessionContext;

@Transactional(readOnly = true)
public abstract class AbstractEntityService<T, ID extends Serializable , CONTEXT extends SessionContext> extends AbstractService<CONTEXT>  implements
EntityService<T, ID> , SerpicsService<CONTEXT>{

    public abstract Repository<T, ID> getEntityRepository();

    @Override
    @Transactional
    public T create(final T entity) {
        return getEntityRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public T update(final T entity) {
        return getEntityRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(final T entity) {
        getEntityRepository().delete(entity);
    }

    @Override
    @Transactional
    public void delete(final ID id) {
        getEntityRepository().delete(id);
    }

    @Override
    public Page<T> findAll(final Pageable page) {
        return getEntityRepository().findAll(page);
    }

    @Override
    public List<T> findAll() {
        return getEntityRepository().findAll();
    }



    @Override
    public T findOne(final ID id) {
        return getEntityRepository().findOne(id);
    }
    
    @Override
    public T findByUUID(final String uuid) {
    	return getEntityRepository().findByUUID(uuid);
    }

    @Override
    @Deprecated
    public void detach(final T entity) {
        getEntityRepository().detach(entity);
    }

}
