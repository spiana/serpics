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

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


@SuppressWarnings("unused")
public class PropertiesLoader implements InitializingBean {

	private String pattern ;
	
	@Required
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public void load() throws IOException{
		
		Properties p = new Properties();
		PathMatchingResourcePatternResolver _p = new PathMatchingResourcePatternResolver();
		Resource[] r = _p.getResources(this.pattern);
		for (Resource resource : r) {
			p.load(resource.getInputStream());
		}
		
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		load();
	}
}

