/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.config;

import java.util.Map;


/**
 * @author spiana
 *
 */
public class ComponentBean {

	 Map<String, Class<?>> storeImpl;
     String scope;

     public ComponentBean(final Map<String, Class<?>> storeImpl, final String scope) {
         this.setStoreImpl(storeImpl);
         this.setScope(scope);
     }

	public Map<String, Class<?>> getStoreImpl() {
		return storeImpl;
	}

	public void setStoreImpl(Map<String, Class<?>> storeImpl) {
		this.storeImpl = storeImpl;

	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
