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

import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.core.Engine;
import com.serpics.core.session.SessionContext;

public class AbstractComponent {

    @Autowired(required = true)
    private transient Engine<SessionContext> commerceEngine;
    
    public Engine<SessionContext> getCommerceEngine() {
        return commerceEngine;
    }

    public void setCommerceEngine(final Engine<SessionContext> commerceEngine) {
        this.commerceEngine = commerceEngine;
    }


    protected SessionContext getCurrentContext() {
        return commerceEngine.getCurrentContext();
    }

    protected boolean hasRole( final String role){
        return hasRoles(new String[] {role});
    }

    protected boolean hasRoles(final String[] roles){
        //TODO: to implement 
        return true;
    }

    protected boolean isInGroup(final String group){

        return isInGroups(new String[] {group});
    }
    protected boolean isInGroups(final String[] groups){
        //TODO: to implement
        return true;
    }
}
