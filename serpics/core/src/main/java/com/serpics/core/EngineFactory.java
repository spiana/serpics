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
package com.serpics.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EngineFactory implements ApplicationContextAware{
	private static transient Logger LOG = LoggerFactory.getLogger(EngineFactory.class);
	
	private static final Map<ClassLoader, ApplicationContext> currentContextPerThread = new ConcurrentHashMap<ClassLoader, ApplicationContext>(1);
	private static List<String> activeModules = new ArrayList<String>();
	
	
	public static void init()  {
		String home_directory  = System.getProperty("serpics.home");
		LOG.info("home directory [{}]" , home_directory);
		URL l = null;
		if (home_directory != null){
			if(!home_directory.endsWith(File.separator))
				home_directory += File.separator;
				File f = new File(home_directory +"config/modules.xml");
				if(f.exists()){
					try {
						l = Paths.get(f.getPath()).toUri().toURL();
					} catch (MalformedURLException e) {
							
					}
				}else{
					LOG.info("file modules.xml not found in home directory try to load from classpath !");
				}
		}
		if (l == null)
			l = Thread.currentThread().getContextClassLoader().getResource("META-INF/modules.xml");
		
		init(l);
	}
	
	public static void init(URL l ){
		List<String> modulesURL = new ArrayList<String>();
		
		
		modulesURL.add("classpath:META-INF/applicationContext.xml");
		if(l == null)
			LOG.info("no modules.xml found in classpath !");
		else{	
			LOG.info("modules file location : {}",l.getPath());
			SAXReader reader = new SAXReader();
		    Document document;
		    
			try {
				document = reader.read(l);
				  
			     Element root = document.getRootElement();
			     for ( Iterator<?> i = root.elementIterator( "module" ); i.hasNext(); ) {
			            Element node = (Element) i.next();
			            String module = node.getText();
						LOG.info("adding module : {}" , module);
						if (!module.endsWith("-serpics.xml")){
							module = module+ "-serpics.xml";
						}
						activeModules.add(module.replace("-serpics.xml", ""));
						
						module = "classpath*:META-INF/"+ module;
						modulesURL.add(module);
			        }
				
			     	
			} catch (DocumentException e) {
				LOG.error("",e);
			}
		}
		
			
		
		ApplicationContext context = new ClassPathXmlApplicationContext(modulesURL.toArray(new String[]{}));
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), context);
		
	}
	public void setApplicationContext(ApplicationContext appContext) {
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), appContext);
	}
	
	public final static ApplicationContext getCurrentApplicationContext() {
		return currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
	}
	
	public final static List<String> getActiveModule(){
		return activeModules;
	}
}
