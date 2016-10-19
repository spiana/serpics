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
package com.serpics.vaadin.ui;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Component;
import com.vaadin.ui.TableFieldFactory;

public interface EntityComponent<T> extends Component{

    public void init();

    public boolean isInitialized();

    public void discard();

    public void save() throws CommitException;
    public Class<T> getEntityType();
  
    public interface MasterTableComponent<T> extends EntityComponent<T>{
        public void setTableFieldFactory(TableFieldFactory factory);
    }

    public interface EntityComponentChild<DETAIL,MASTER> extends EntityComponent<DETAIL> {
        public void setParentEntity(EntityItem<MASTER> parent);
        public void setParentProperty(Object parentPropertyId);

       
    }
    public interface EntityFormComponent<T> extends EntityComponent<T>{
        public boolean isModifield();
        public boolean isValid();
        public void setEntityItem(EntityItem<T> entityItem);

    }


}
