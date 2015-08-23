package com.serpics.core;

import java.net.URL;
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

	public static void init()  {
		List<String> modules = new ArrayList<String>();
		modules.add("classpath:META-INF/applicationContext.xml");
		URL l = Thread.currentThread().getContextClassLoader().getResource("META-INF/modules.xml");
		LOG.info("modules file location : {}",l.getPath());
		
		SAXReader reader = new SAXReader();
	     Document document;
		try {
			document = reader.read(l);
			  
		     Element root = document.getRootElement();
		     for ( Iterator i = root.elementIterator( "module" ); i.hasNext(); ) {
		            Element node = (Element) i.next();
		            String module = node.getText();
					LOG.info("adding module : {}" , module);
					module = "classpath*:META-INF/"+ module;
					modules.add(module);
		        }
			
		} catch (DocumentException e) {
			LOG.error("",e);
		}
	     
		
			
		
		ApplicationContext context = new ClassPathXmlApplicationContext(modules.toArray(new String[]{}));
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), context);
	}

	public void setApplicationContext(ApplicationContext appContext) {
		currentContextPerThread.put(Thread.currentThread().getContextClassLoader(), appContext);
	}
	
	public final static ApplicationContext getCurrentApplicationContext() {
		return currentContextPerThread.get(Thread.currentThread().getContextClassLoader());
	}
}
