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

