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
package com.serpics.spring.context.support;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

import com.serpics.core.EngineFactoryUtils;

public class CustomRelodableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource implements InitializingBean , ApplicationContextAware{
	private ApplicationContext context;
	
	private transient static Logger LOG = LoggerFactory.getLogger(CustomRelodableResourceBundleMessageSource.class);
	
	@Override
	public void setApplicationContext(ApplicationContext paramApplicationContext)
			throws BeansException {
		this.context =paramApplicationContext;
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		List<Resource> resources = EngineFactoryUtils.loadResourceByModule(context, "classpath*:META-INF/", "-messages*.properties");
		List<String> baseNames = new ArrayList<String>();
		
		for (Resource resource : resources) {
			String[] baseFile = resource.getFilename().split("\\.");
			String _baseFile = baseFile[0];
			if (baseFile[0].contains("_"))
				_baseFile = baseFile[0].split("_")[0];
			
			_baseFile = "classpath:META-INF/" + _baseFile;
			if (!baseNames.contains(_baseFile)){
				baseNames.add(_baseFile);
				LOG.info("found message file : {} ",  _baseFile);
			}
			
		}
		setBasenames((String[]) baseNames.toArray(new String[baseNames.size()]));
	}

}
