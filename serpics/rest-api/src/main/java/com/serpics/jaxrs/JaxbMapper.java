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
package com.serpics.jaxrs;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class JaxbMapper  extends ObjectMapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4414515344211684147L;

	public JaxbMapper() {
		super();
		AnnotationIntrospector introspector2 = new JacksonAnnotationIntrospector();
//		AnnotationIntrospector introspector1 = new JacksonAnnotationIntrospector();
//		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(introspector2, introspector1);				
		super.setAnnotationIntrospector(introspector2);
		configure(MapperFeature.USE_ANNOTATIONS, true);
		configure(MapperFeature.AUTO_DETECT_FIELDS , true);
		configure(MapperFeature.AUTO_DETECT_GETTERS, true);
//		setSerializationInclusion(Inclusion.NON_NULL);
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		setDateFormat(dateFormat);
		
	}
}
