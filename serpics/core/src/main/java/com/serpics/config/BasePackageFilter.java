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

import com.impetus.annovention.Filter;

/**
 * @author spiana
 *
 */
public class BasePackageFilter implements Filter {
	
	String basePackage;
	
	public BasePackageFilter(String basePackage) {
		super();
		this.basePackage = basePackage;
	}

	/* (non-Javadoc)
	 * @see com.impetus.annovention.Filter#accepts(java.lang.String)
	 */
	@Override
	public boolean accepts(String paramString) {
		 if (paramString.endsWith(".class")) {
			if (paramString.startsWith("/")) {
				paramString = paramString.substring(1);
			}
			if (paramString.replace('/', '.').startsWith(basePackage))
				return true;
			
		}
		return false;
	}
	
}
